package org.binar.ChallengeChapter6BackEndJava.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponseJson {
    @Schema(example = "Server error, tidak dapat menanggapi request")
    private String message;
    @Schema(example = "500")
    private String statusCode;
}
