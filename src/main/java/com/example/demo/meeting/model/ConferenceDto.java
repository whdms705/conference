package com.example.demo.meeting.model;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ConferenceDto {
    @NotNull
    private String conName;

    private String conUser;
    private String conDate;
    private String startTime;
    private String endTime;
    private int isRepeat;
}
