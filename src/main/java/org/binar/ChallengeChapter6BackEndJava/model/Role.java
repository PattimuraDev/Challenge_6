package org.binar.ChallengeChapter6BackEndJava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.binar.ChallengeChapter6BackEndJava.model.enumerations.ERole;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @Schema(example = "CUSTOMER")
    private ERole name;
}
