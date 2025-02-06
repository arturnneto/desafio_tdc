package com.artur.estoqueapi.service.stock;

import com.artur.estoqueapi.domain.dto.security.LoginRequestDto;
import com.artur.estoqueapi.domain.entities.auth.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity saveNewUser(UserEntity userEntity);

    UserEntity createNewBasicUser(UserEntity userEntity);

    void checkUserExistence(UserEntity userEntity);

    Optional<UserEntity> getUserEntity(UserEntity userEntity);

    Optional<UserEntity> getUserLoginEntity(LoginRequestDto loginRequestDto);
}
