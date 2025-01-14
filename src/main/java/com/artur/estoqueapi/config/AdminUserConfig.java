package com.artur.estoqueapi.config;

import com.artur.estoqueapi.domain.entities.auth.RoleEntity;
import com.artur.estoqueapi.domain.entities.auth.UserEntity;
import com.artur.estoqueapi.repositories.RoleRepository;
import com.artur.estoqueapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var adminRole = roleRepository.findByRoleName(RoleEntity.Values.ADMIN.name().toLowerCase());
        var adminUser = userRepository.findByUsername("admin");

        adminUser.ifPresentOrElse(
                user -> {
                    System.out.println("Admin user already exists.");
                },
                () -> {
                    var user = new UserEntity();
                    user.setUsername("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));
                    user.setRoles(Set.of(adminRole));
                    userRepository.save(user);
                }
        );
    }
}
