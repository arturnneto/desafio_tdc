package com.artur.callforpapers.controllers.security;

import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import com.artur.callforpapers.domain.dto.security.LoginResponseDto;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.service.security.TokenService;
import com.artur.callforpapers.service.papers.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
public class TokenController {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    private static final String ISSUER = "CallForPapers";
    private static final Long tokenExpirationTime = 300L;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto) {
        Optional<UserEntity> userFromDatabase = userService.getUserLoginEntity(loginRequestDto);
        tokenService.checkUserExistence(userFromDatabase);
        tokenService.checkIfLoginIsCorrect(loginRequestDto, bCryptPasswordEncoder);
        String scopes = tokenService.generateTokenScopes(userFromDatabase);
        JwtClaimsSet claims = tokenService.generateJwtClaimsSet(userFromDatabase, ISSUER, tokenExpirationTime, scopes);
        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDto(jwtValue, tokenExpirationTime));
    }
}
