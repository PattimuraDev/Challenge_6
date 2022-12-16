package org.binar.ChallengeChapter6BackEndJava;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class ChallengeChapter6BackEndJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChallengeChapter6BackEndJavaApplication.class, args);
    }
}
