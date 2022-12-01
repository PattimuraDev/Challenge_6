package org.binar.ChallengeChapter6BackEndJava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {
    @EmbeddedId
    private SeatNumberCompositeKey seatNumberCompositeKey;
    @Schema(example = "Studio D")
    private String studioName;
    @Schema(example = "available")
    private String status;
    @Column(name = "schedule_id")
    @Schema(example = "1")
    private Long scheduleID;
}
