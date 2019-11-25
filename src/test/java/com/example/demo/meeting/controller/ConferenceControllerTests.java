package com.example.demo.meeting.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(ConferenceController.class) // web과 관련된 bean생
// OjectMapper

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConferenceControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 전체_conferense_정보조회_200_테스트()throws Exception{
        // /all
        mockMvc.perform(get("/conference/all"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void 날짜_회의실기준_정보조회_200_테스트()throws  Exception{
        // /all
        mockMvc.perform(get("/conference/con-name/B/con-date/2019-11-17"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }


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
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void 예약시간등록_bad_request_400_테스트()throws Exception{

        String requestBody = "{\"conName\":\"B\", \"conUser\":\"eunho\", \"conDate\":\"2019-11-17\",\"startTime\":\"10:35\",\"endTime\":\"11:30\"}";

        mockMvc.perform(
                post("/conference/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andDo(print())
                //.andExpect(jsonPath("Error message").value(ErrorMessage.INVALID_TIME_FORMAT))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void 예약시간등록_bad_request_201_테스트()throws Exception{

        String requestBody = "{\"conName\":\"B\", \"conUser\":\"eunho\", \"conDate\":\"2019-11-17\",\"startTime\":\"10:30\",\"endTime\":\"11:30\"}";

        mockMvc.perform(
                post("/conference/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andDo(print())
                //.andExpect(jsonPath("Error message").value(ErrorMessage.INVALID_TIME_FORMAT))
                .andExpect(status().is2xxSuccessful());

    }



}
