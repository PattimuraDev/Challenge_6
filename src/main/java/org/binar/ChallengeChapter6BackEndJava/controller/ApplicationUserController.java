package org.binar.ChallengeChapter6BackEndJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.ChallengeChapter6BackEndJava.model.ApplicationUser;
import org.binar.ChallengeChapter6BackEndJava.model.Role;
import org.binar.ChallengeChapter6BackEndJava.model.dto.ApplicationUserDto;
import org.binar.ChallengeChapter6BackEndJava.model.enumerations.ERole;
import org.binar.ChallengeChapter6BackEndJava.model.response.CustomResponseJson;
import org.binar.ChallengeChapter6BackEndJava.repository.RoleRepository;
import org.binar.ChallengeChapter6BackEndJava.service.ApplicationUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Tag(name = "USER")
@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    @Autowired
    ApplicationUserDetailServiceImpl applicationUserDetailService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Method controller untuk mengakomodasi kebutuhan mengupdate data user
     * @param idUsers  paramater untuk id dari user
     * @param applicationUserDto parameter untuk data transfer object dari user
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk mengupdate data user")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(
            responseCode = "200",
            description = "Data user berhasil diupdate",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationUserDto.class)
            )
    )
    @PutMapping("/update_user/{id}")
    public ResponseEntity<ApplicationUser> updateUsers(@PathVariable("id") Long idUsers, @RequestBody ApplicationUserDto applicationUserDto) {
        ApplicationUser applicationUser = new ApplicationUser(
                null,
                applicationUserDto.getUsername(),
                applicationUserDto.getEmail(),
                passwordEncoder.encode(applicationUserDto.getPassword())
        );
        Set<String> strRoles = applicationUserDto.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role role = roleRepository.findByName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role tidak ditemukan"));
            roles.add(role);
        } else {
            strRoles.forEach(role -> {
                Role roles1 = roleRepository.findByName(ERole.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + " tidak ditemukan"));
                roles.add(roles1);
            });
        }
        applicationUser.setRoles(roles);
        final ApplicationUser result = applicationUserDetailService.updateUser(idUsers, applicationUser);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan untuk menghapus data user berdasarkan id user
     * @param idUser parameter untuk id dari user
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menghapus data user")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(
            responseCode = "200",
            description = "Data user berhasil dihapus",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomResponseJson.class)
            )
    )
    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<CustomResponseJson> deleteUsers(@PathVariable("id") Long idUser) {
        try {
            applicationUserDetailService.deleteUser(idUser);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "User berhasil dihapus",
                            "200"
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Anda tidak memiliki otorisasi untuk menghapus user",
                            "403"
                    ),
                    HttpStatus.FORBIDDEN
            );
        }
    }
}
