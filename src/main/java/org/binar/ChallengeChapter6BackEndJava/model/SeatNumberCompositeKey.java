package org.binar.ChallengeChapter6BackEndJava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatNumberCompositeKey implements Serializable {
    @Schema(example = "23")
    private String nomorBarisKursi;
    @Schema(example = "E")
    private String nomorKolomKursi;
}
