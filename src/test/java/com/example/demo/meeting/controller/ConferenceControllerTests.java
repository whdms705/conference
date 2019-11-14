package com.example.demo.meeting.controller;

import com.example.demo.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
//@WebMvcTest(ConferenceController.class) // web과 관련된 bean생
@SpringBootTest
@AutoConfigureMockMvc
public class ConferenceControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 예약시간등록_200() throws Exception{
        mockMvc.perform(post("/conference/reservation")
                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isCreated())
                ;
    }

    @Test
    public void 예약시간등록_bad_request_400()throws Exception{

        String requestBody = "{\"value1\":5, \"operation\":\"+\", \"value2\":10,\"result\":15}";

        mockMvc.perform(
                post("/conference/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isNotFound());

    }



}
