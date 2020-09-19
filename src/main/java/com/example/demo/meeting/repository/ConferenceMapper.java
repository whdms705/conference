package com.example.demo.meeting.repository;

import com.example.demo.meeting.model.Conference;
import com.example.demo.meeting.model.ConferenceDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConferenceMapper {

    @Cacheable("selectAllConference")
    List<Conference> selectAllConference();

    int insertConference(Conference conference);

    List<Conference> selectConTimeByConDate(ConferenceDto conferenceDto);
}
