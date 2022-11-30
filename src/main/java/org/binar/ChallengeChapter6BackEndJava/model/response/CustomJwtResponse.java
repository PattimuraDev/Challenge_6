package org.binar.ChallengeChapter6BackEndJava.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CustomJwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public CustomJwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}
