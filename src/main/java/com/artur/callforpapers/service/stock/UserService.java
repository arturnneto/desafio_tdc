package com.artur.callforpapers.service.stock;

import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import com.artur.callforpapers.domain.entities.auth.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity saveNewUser(UserEntity userEntity);

    UserEntity createNewBasicUser(UserEntity userEntity);

    void checkUserExistence(UserEntity userEntity);

    Optional<UserEntity> getUserEntity(UserEntity userEntity);

    Optional<UserEntity> getUserLoginEntity(LoginRequestDto loginRequestDto);
}
