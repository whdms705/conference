package com.example.demo.meeting.repository;


import com.example.demo.meeting.model.Conference;
import com.example.demo.meeting.model.ConferenceDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConferenceMapperTests {

    @Autowired
    ConferenceMapper conferenceMapper;

    ConferenceDto conferenceDto = null;

    @Before
    public void setup(){
        conferenceDto = new ConferenceDto();
        conferenceDto.setConName("B");
        conferenceDto.setConDate("2019-11-15");

    }

    @Test
    public void 해당날짜에_선택한회의실_현황조회_테스트(){
        List<Conference> list = conferenceMapper.selectConTimeByConDate(conferenceDto);
        assertTrue(!list.isEmpty());
    }

}
