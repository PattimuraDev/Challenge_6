package org.binar.ChallengeChapter6BackEndJava.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.ChallengeChapter6BackEndJava.configuration.JwtUtils;
import org.binar.ChallengeChapter6BackEndJava.model.ApplicationUser;
import org.binar.ChallengeChapter6BackEndJava.model.ApplicationUserDetailsImpl;
import org.binar.ChallengeChapter6BackEndJava.model.Role;
import org.binar.ChallengeChapter6BackEndJava.model.dto.ApplicationUserDto;
import org.binar.ChallengeChapter6BackEndJava.model.dto.SignInRequestDto;
import org.binar.ChallengeChapter6BackEndJava.model.enumerations.ERole;
import org.binar.ChallengeChapter6BackEndJava.model.response.CustomJwtResponse;
import org.binar.ChallengeChapter6BackEndJava.model.response.CustomMessageResponse;
import org.binar.ChallengeChapter6BackEndJava.repository.ApplicationUserRepository;
import org.binar.ChallengeChapter6BackEndJava.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "AUTHENTICATION")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public LoginController() {

    }

    public LoginController(AuthenticationManager authenticationManager, ApplicationUserRepository inputApplicationUserRepository,
                           JwtUtils jwtUtils, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.applicationUserRepository = inputApplicationUserRepository;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @SecurityRequirements
    @PostMapping("/signin")
    public ResponseEntity<CustomJwtResponse> authenticateUser(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getUsername(), signInRequestDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        ApplicationUserDetailsImpl userDetails = (ApplicationUserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new CustomJwtResponse(jwt,
                userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                roles));
    }

    @SecurityRequirements
    @PostMapping("/signup")
    public ResponseEntity<CustomMessageResponse> registerUser(@Valid @RequestBody ApplicationUserDto applicationUserDto) {
        Boolean usernameIsExist = applicationUserRepository.existsByUsername(applicationUserDto.getUsername());
        if (Boolean.TRUE.equals(usernameIsExist)) {
            return ResponseEntity.badRequest()
                    .body(new CustomMessageResponse("Error: Username sudah ada!"));
        }

        Boolean emailIsExist = applicationUserRepository.existsByEmail(applicationUserDto.getEmail());
        if (Boolean.TRUE.equals(emailIsExist)) {
            return ResponseEntity.badRequest()
                    .body(new CustomMessageResponse("Error: Email sudah ada!"));
        }

        ApplicationUser applicationUser = new ApplicationUser(null, applicationUserDto.getUsername(), applicationUserDto.getEmail(),
                passwordEncoder.encode(applicationUserDto.getPassword()));

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
        applicationUserRepository.save(applicationUser);
        return ResponseEntity.ok(new CustomMessageResponse("Registrasi user berhasil"));
    }


}

