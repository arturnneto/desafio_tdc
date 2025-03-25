package com.artur.callforpapers.service.security.impl;

import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import com.artur.callforpapers.domain.entities.auth.RoleEntity;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.service.security.TokenService;
import com.artur.callforpapers.service.papers.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final UserService userService;

    @Override
    public void checkUserExistence(Optional<UserEntity> userFromDatabase) {
        if (userFromDatabase.isEmpty()) {
            throw new BadCredentialsException("User or password is not valid.");
        }
    }

    @Override
    public void checkIfLoginIsCorrect(LoginRequestDto loginRequestDto, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Optional<UserEntity> userFromDatabase = userService.getUserLoginEntity(loginRequestDto);

        if (userFromDatabase.isEmpty() || !userFromDatabase.get().isLoginCorrect(loginRequestDto, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("User or password is not valid.");
        }
    }

    @Override
    public String generateTokenScopes(Optional<UserEntity> userFromDatabase) {
        return userFromDatabase.get().getRoles()
                .stream()
                .map(RoleEntity::getRoleName)
                .collect(Collectors.joining(" "));
    }

    @Override
    public JwtClaimsSet generateJwtClaimsSet(Optional<UserEntity> userFromDatabase, String issuer, Long expiresIn, String scopes) {
        Instant now = Instant.now();

        return JwtClaimsSet.builder()
                .issuer(issuer)
                .subject(userFromDatabase.get().getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();
    }
}
