package org.binar.ChallengeChapter6BackEndJava.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
