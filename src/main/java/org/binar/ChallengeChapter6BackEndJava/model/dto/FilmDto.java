package org.binar.ChallengeChapter6BackEndJava.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.binar.ChallengeChapter6BackEndJava.model.Schedule;
import java.util.List;

@Data
public class FilmDto {
    @Schema(example = "1")
    private Long filmCode;
    @Schema(example = "Batman Gotham City")
    private String filmName;
    @Schema(example = "true")
    private Boolean isPlaying;
    private List<Schedule> schedulesList;
}
