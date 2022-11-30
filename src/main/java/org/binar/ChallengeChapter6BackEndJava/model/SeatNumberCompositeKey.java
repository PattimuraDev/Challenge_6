package org.binar.ChallengeChapter6BackEndJava.model;

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
    private String nomorBarisKursi;
    private String nomorKolomKursi;
}
