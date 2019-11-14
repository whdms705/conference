package com.example.demo.meeting.repository;

import com.example.demo.meeting.model.Conference;
import com.example.demo.meeting.model.ConferenceDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConferenceMapper {
    List<Conference> selectAllConference();
    int insertConference(Conference conference);
    List<Conference> selectConTimeByConDate(ConferenceDto conferenceDto);
}
