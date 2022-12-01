package org.binar.ChallengeChapter6BackEndJava.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomMessageResponse {
    @Schema(example = "Operasi berhasil")
    private String message;
}
