package com.example.demo.meeting.service;


import com.example.demo.common.TimeMap;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorMessage;
import com.example.demo.meeting.model.Conference;
import com.example.demo.meeting.model.ConferenceDto;
import com.example.demo.meeting.repository.ConferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
public class ConferenceService {

    @Autowired
    ConferenceMapper conferenceMapper;

    public List<Conference> selectAllConference(){
        return conferenceMapper.selectAllConference();
    }

    public int insertConference(ConferenceDto conferenceDto, BindingResult bindingResult){
        if((bindingResult.hasErrors()) || checkFormatTime(conferenceDto)){
            throw new ApiException(
                    ErrorMessage.INVALID_TIME_FORMAT.getErrorMessage(),
                    HttpStatus.BAD_REQUEST,
                    ErrorMessage.INVALID_TIME_FORMAT.getErrorCode()
            );
        }
        Conference conference =  this.convertConTime(conferenceDto);
        return conferenceMapper.insertConference(conference);
    }

    public boolean isAvailReservation(ConferenceDto conferenceDto){
        List<Conference> list = conferenceMapper.selectConTimeByConDate(conferenceDto);
        List<String> timeList = list.stream().map(l -> l.getConTime()).collect(Collectors.toList());

        return false;
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

            TimeMap time = new TimeMap();
            for(int i=time.map.get(start);i<=time.map.get(end);i++){
                conTime = conTime+String.valueOf(i)+",";
            }

            conference.setConTime(conTime);

            return conference;
    }

}
