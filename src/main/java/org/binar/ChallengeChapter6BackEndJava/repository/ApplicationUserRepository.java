package org.binar.ChallengeChapter6BackEndJava.repository;

import org.binar.ChallengeChapter6BackEndJava.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String emailAddress);
}
