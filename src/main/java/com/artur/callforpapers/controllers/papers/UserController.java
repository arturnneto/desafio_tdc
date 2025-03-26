package com.artur.callforpapers.controllers.papers;

import com.artur.callforpapers.domain.dto.papers.UserDto;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.mappers.Mapper;
import com.artur.callforpapers.repositories.UserRepository;
import com.artur.callforpapers.service.papers.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Mapper<UserEntity, UserDto> userMapper;


    @Transactional
    @PostMapping("/users")
    public ResponseEntity<UserDto> createNewBasicUser(@Valid @RequestBody UserDto userDto) {
        UserEntity userToBeCreated = userMapper.mapFrom(userDto);
        UserEntity newUser = userService.createNewBasicUser(userToBeCreated);
        userService.saveNewUser(newUser);

        return new ResponseEntity<>(userMapper.mapTo(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<UserEntity>> listAllUsers() {
        List<UserEntity> usersList = userRepository.findAll();
        return ResponseEntity.ok(usersList);
    }



}
