package com.artur.estoqueapi.service.stock.impl;

import com.artur.estoqueapi.domain.dto.security.LoginRequestDto;
import com.artur.estoqueapi.domain.dto.stock.UserCreationDto;
import com.artur.estoqueapi.domain.entities.auth.RoleEntity;
import com.artur.estoqueapi.domain.entities.auth.UserEntity;
import com.artur.estoqueapi.repositories.UserRepository;
import com.artur.estoqueapi.service.stock.RoleService;
import com.artur.estoqueapi.service.stock.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final RoleService roleService;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public void checkUserExistence(UserCreationDto newUserDto) {
        Optional<UserEntity> userFromDatabase = userRepository.findByUsername(newUserDto.username());

        if (userFromDatabase.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public UserEntity createNewBasicUser(UserCreationDto newUserDto) {
        checkUserExistence(newUserDto);
        RoleEntity basicUserRole = roleService.getBasicRole();

        UserEntity newUser = new UserEntity();
        newUser.setUsername(newUserDto.username());
        newUser.setPassword(bCryptPasswordEncoder.encode(newUserDto.password()));
        newUser.setRoles(Set.of(basicUserRole));

        return newUser;
    }

    @Override
    public Optional<UserEntity> getUserEntity(LoginRequestDto loginRequestDto) {
        return userRepository.findByUsername(loginRequestDto.username());
    }
}
