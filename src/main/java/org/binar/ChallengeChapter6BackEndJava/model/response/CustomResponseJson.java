package org.binar.ChallengeChapter6BackEndJava.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponseJson {
    private String message;
    private String statusCode;
}
