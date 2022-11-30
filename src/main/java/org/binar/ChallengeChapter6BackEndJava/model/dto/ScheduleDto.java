package org.binar.ChallengeChapter6BackEndJava.model.dto;

import lombok.Data;

@Data
public class ScheduleDto {
    private Long scheduleId;
    private Long filmCode;
    private String date;
    private String startTime;
    private String endTime;
    private Long ticketPrice;
    private String studioName;
}
