package com.example.demo.meeting.controller;

import com.example.demo.meeting.model.ConferenceDto;
import com.example.demo.meeting.repository.ConferenceMapper;
import com.example.demo.meeting.service.ConferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/conference")
public class ConferenceController {


    //@Qualifier("conferenceService")
    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ConferenceMapper conferenceMapper;


    @PostMapping("/reservation")
    public void uploadData(@RequestBody @Valid ConferenceDto conferenceDto, BindingResult bindingResult){

        log.info("111111");
        int result = conferenceService.insertConference(conferenceDto,bindingResult);
        /*return ApiResult.builder()
                .status(HttpStatus.OK.value())
                .build();*/
    }

    @GetMapping("/con-name/{conName}/con-date/{conDate}")
    public Object getConTime(@PathVariable String conDate,@PathVariable String conName){
        ConferenceDto conferenceDto = new ConferenceDto();
        conferenceDto.setConDate(conDate);
        conferenceDto.setConName(conName);
        return conferenceMapper.selectConTimeByConDate(conferenceDto);
    }

    @GetMapping("/all")
    public Object getAllConInfo(){
        return conferenceMapper.selectAllConference();
    }


    @GetMapping("/test")
    public Object test(){
        for(int i=0;i<1000000;i++){
            ConferenceDto conferenceDto = new ConferenceDto();
        }
        return "1";
    }

}
