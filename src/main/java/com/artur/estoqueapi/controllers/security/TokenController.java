package com.artur.estoqueapi.controllers.security;

import com.artur.estoqueapi.domain.dto.security.LoginRequestDto;
import com.artur.estoqueapi.domain.dto.security.LoginResponseDto;
import com.artur.estoqueapi.domain.entities.auth.UserEntity;
import com.artur.estoqueapi.service.security.TokenService;
import com.artur.estoqueapi.service.stock.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class TokenController {

    private JwtEncoder jwtEncoder;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenService tokenService;
    private UserService userService;
    private static final String ISSUER = "EstoqueAPI";
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
