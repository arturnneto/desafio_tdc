package com.artur.callforpapers.domain.dto.papers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {

    @NotBlank(message = "O nome de usuário não pode estar vazio")
    @Size(min = 6, message = "O username deve ter pelo menos 6 caracteres")
    private String username;

    @NotBlank(message = "A senha não pode estar vazia")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String password;
}
