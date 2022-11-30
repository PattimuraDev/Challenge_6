package org.binar.ChallengeChapter6BackEndJava.repository;

import org.binar.ChallengeChapter6BackEndJava.model.Role;
import org.binar.ChallengeChapter6BackEndJava.model.enumerations.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
