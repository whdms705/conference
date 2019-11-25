package com.example.demo.meeting.service;


import com.example.demo.common.TimeMap;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorMessage;
import com.example.demo.meeting.model.Conference;
import com.example.demo.meeting.model.ConferenceDto;
import com.example.demo.meeting.repository.ConferenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class ConferenceService {

    @Autowired
    ConferenceMapper conferenceMapper;

    public List<Conference> selectAllConference(){
        return conferenceMapper.selectAllConference();
    }

    public int insertConference(ConferenceDto conferenceDto, BindingResult bindingResult){

        // parameter format validation
        if((bindingResult.hasErrors()) || checkFormatTime(conferenceDto)){
            throw new ApiException(
                    ErrorMessage.INVALID_TIME_FORMAT.getErrorMessage(),
                    HttpStatus.BAD_REQUEST,
                    ErrorMessage.INVALID_TIME_FORMAT.getErrorCode()
            );
        }

        // reservation validation
        Conference conference =  this.convertConTime(conferenceDto);
        return conferenceMapper.insertConference(conference);
    }

    public boolean isAvailReservation(ConferenceDto conferenceDto,String conTime){
        boolean result = false;
        LocalDateTime now = LocalDateTime.now();

        Set<String> set = new HashSet<>();
        String[] requestTime = conTime.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        for(int i=0;i<conferenceDto.getIsRepeat()+1;i++){
            set.clear();
            String changeTime = now.plusWeeks(i).format(formatter);
log.info("changeTime : ",changeTime);
            conferenceDto.setConDate(changeTime);
            List<Conference> list = conferenceMapper.selectConTimeByConDate(conferenceDto);
            String[] conTimes = list.stream().map(Conference::getConTime).reduce((r1,r2) -> r1+r2).get().split(",");
            for(String s: conTimes){
                set.add(s);
            }
            int repeatCheck = 0;
            for(String s: requestTime){
                if(set.contains(s)){
                    repeatCheck++;
                }
                if(repeatCheck>=2){
                    result = true;
                    break;

                }
            }


        }

        System.out.println("1");

        return result;
    }

    public boolean checkFormatTime(ConferenceDto conferenceDto){
        boolean check = true;
        String[] startTime = conferenceDto.getStartTime().split(":");
        String[] endTime = conferenceDto.getEndTime().split(":");

        int startHour = LocalTime.parse(conferenceDto.getStartTime()).getHour();
        int endHour = LocalTime.parse(conferenceDto.getEndTime()).getHour();

        if((startHour < 9 || startHour > 19) || (endHour < 9 || endHour > 19) || startHour > endHour){
            return true;
        }

        if(("00".equals(startTime[1]) || "30".equals(startTime[1])) && ("00".equals(endTime[1]) || "30".equals(endTime[1]))){
            return false;
        }

        return  check;
    }



    public Conference convertConTime(ConferenceDto conferenceDto){
            Conference conference = new Conference();
            conference.setConUser(conferenceDto.getConUser());
            conference.setConName(conferenceDto.getConName());
            conference.setConDate(conferenceDto.getConDate());
            conference.setIsRepeat(conferenceDto.getIsRepeat());
            String start = conferenceDto.getStartTime();
            String end = conferenceDto.getEndTime();

            String conTime = "";

            TimeMap time = TimeMap.getInstance();

            for(int i=time.get(start);i<=time.get(end);i++){
                conTime = conTime+String.valueOf(i)+",";
            }

            conference.setConTime(conTime);

            return conference;
    }

}
