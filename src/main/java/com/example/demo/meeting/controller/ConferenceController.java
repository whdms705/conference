package com.example.demo.meeting.controller;

import com.example.demo.exception.ApiException;
import com.example.demo.meeting.model.ApiResult;
import com.example.demo.meeting.model.ConferenceDto;
import com.example.demo.meeting.service.ConferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/conference")
public class ConferenceController {


    @Qualifier("conferenceService")
    private ConferenceService conferenceService;


    @PostMapping("/reservation")
    public void uploadData(@Valid ConferenceDto conferenceDto, BindingResult bindingResult){

        log.info("111111");
        int result = conferenceService.insertConference(conferenceDto,bindingResult);
        /*return ApiResult.builder()
                .status(HttpStatus.OK.value())
                .build();*/
    }


}
