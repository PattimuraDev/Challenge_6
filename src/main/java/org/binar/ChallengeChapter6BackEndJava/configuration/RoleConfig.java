package org.binar.ChallengeChapter6BackEndJava.configuration;

import org.binar.ChallengeChapter6BackEndJava.model.Role;
import org.binar.ChallengeChapter6BackEndJava.model.enumerations.ERole;
import org.binar.ChallengeChapter6BackEndJava.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RoleConfig.class);

    RoleConfig(RoleRepository roleRepository) {
        LOG.info("Mengecek role tersedia");
        for (ERole c : ERole.values()) {
            try {
                Role roles = roleRepository.findByName(c)
                        .orElseThrow(() -> new RuntimeException("Roles tidak ada"));
                LOG.info("Role {} tersedia", roles.getName());
            } catch (RuntimeException rte) {
                LOG.info("Role {} tidak ditemukan, menambahkan role ke database ...", c.name());
                Role roles = new Role();
                roles.setName(c);
                roleRepository.save(roles);
            }
        }
    }

}

