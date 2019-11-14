package com.example.demo.meeting.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("conference")
public class Conference {
    private Long conSeq;
    private String conName;
    private String conUser;
    private String conDate;
    private String conTime;
    private int isRepeat;
    private Timestamp createdAt;
    private Timestamp updateAt;
}
