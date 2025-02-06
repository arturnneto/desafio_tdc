package com.artur.estoqueapi.controllers.stock;

import com.artur.estoqueapi.domain.dto.stock.UserDto;
import com.artur.estoqueapi.domain.entities.auth.UserEntity;
import com.artur.estoqueapi.mappers.Mapper;
import com.artur.estoqueapi.repositories.UserRepository;
import com.artur.estoqueapi.service.stock.UserService;
import jakarta.transaction.Transactional;
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
import java.util.Optional;

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
    public ResponseEntity<UserDto> createNewBasicUser(@RequestBody UserDto userDto) {
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
