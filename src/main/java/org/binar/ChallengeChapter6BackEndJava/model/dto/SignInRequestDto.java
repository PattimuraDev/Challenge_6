package org.binar.ChallengeChapter6BackEndJava.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInRequestDto {
    @Schema(example = "username anda")
    @NotBlank
    private String username;
    @Schema(example = "password anda")
    @NotBlank
    private String password;
}
