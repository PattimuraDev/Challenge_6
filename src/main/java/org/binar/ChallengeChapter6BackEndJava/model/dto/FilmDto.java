package org.binar.ChallengeChapter6BackEndJava.model.dto;

import lombok.Data;
import org.binar.ChallengeChapter6BackEndJava.model.Schedule;

import java.util.List;

@Data
public class FilmDto {
    private Long filmCode;
    private String filmName;
    private Boolean isPlaying;
    private List<Schedule> schedulesList;
}
