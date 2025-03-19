package com.artur.callforpapers.service.security;

import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.util.Optional;

public interface TokenService {
    void checkUserExistence(Optional<UserEntity> userEntity);

    void checkIfLoginIsCorrect(LoginRequestDto loginRequestDto, BCryptPasswordEncoder bCryptPasswordEncoder);

    String generateTokenScopes(Optional<UserEntity> userEntity);

    JwtClaimsSet generateJwtClaimsSet(Optional<UserEntity> userEntity, String issuer, Long expiresIn, String scopes);
}
