package org.binar.ChallengeChapter6BackEndJava.model.dto;

import lombok.Data;
import org.binar.ChallengeChapter6BackEndJava.model.SeatNumberCompositeKey;

@Data
public class SeatDto {
    private SeatNumberCompositeKey seatNumberCompositeKey;
    private String studioName;
    private String status;
    private Long scheduleID;
}
