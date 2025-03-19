package com.artur.callforpapers.domain.entities.auth;

import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    public boolean isLoginCorrect(LoginRequestDto loginRequestDto, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequestDto.password(), this.password);
    }

//    public String getUsername() {
//        return this.username;
//    }
}
