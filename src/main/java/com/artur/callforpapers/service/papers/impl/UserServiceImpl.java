package com.artur.callforpapers.service.papers.impl;

import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import com.artur.callforpapers.domain.entities.auth.RoleEntity;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.repositories.auth.UserRepository;
import com.artur.callforpapers.service.papers.RoleService;
import com.artur.callforpapers.service.papers.UserService;
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
    public Optional<UserEntity> getUserEntity(UserEntity userEntity) {
        return userRepository.findByUsername(userEntity.getUsername());
    }

    @Override
    public Optional<UserEntity> getUserLoginEntity(LoginRequestDto loginRequestDto) {
        return userRepository.findByUsername(loginRequestDto.username());
    }

    @Override
    public UserEntity saveNewUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void checkUserExistence(UserEntity userEntity) {
        Optional<UserEntity> userFromDatabase = getUserEntity(userEntity);

        if (userFromDatabase.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public UserEntity createNewBasicUser(UserEntity newUserEntity) {
        checkUserExistence(newUserEntity);
        RoleEntity basicUserRole = roleService.getBasicRole();

        UserEntity newUser = new UserEntity();
        newUser.setUsername(newUserEntity.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(newUserEntity.getPassword()));
        newUser.setRoles(Set.of(basicUserRole));

        return newUser;
    }
}
