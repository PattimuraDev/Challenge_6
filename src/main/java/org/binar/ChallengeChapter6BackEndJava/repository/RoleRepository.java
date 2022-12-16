package org.binar.ChallengeChapter6BackEndJava.repository;

import org.binar.ChallengeChapter6BackEndJava.model.Role;
import org.binar.ChallengeChapter6BackEndJava.model.enumerations.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Interface repository untuk menghandle semua permintaan ke table role di database
 * @author Dwi Satria Patra
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Method repository untuk menemukan role berdasarkan nama rolenya
     * @param name parameter untuk nama dari role
     * @return role hasil pencarian
     */
    Optional<Role> findByName(ERole name);
}
