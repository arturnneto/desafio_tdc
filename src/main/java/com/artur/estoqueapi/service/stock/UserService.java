package com.artur.estoqueapi.service.stock;

import com.artur.estoqueapi.domain.dto.security.LoginRequestDto;
import com.artur.estoqueapi.domain.dto.stock.UserCreationDto;
import com.artur.estoqueapi.domain.entities.auth.RoleEntity;
import com.artur.estoqueapi.domain.entities.auth.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity createNewBasicUser(UserCreationDto newUserDto);

    void checkUserExistence(UserCreationDto newUserDto);

    public Optional<UserEntity> getUserEntity(LoginRequestDto loginRequestDto);
}
