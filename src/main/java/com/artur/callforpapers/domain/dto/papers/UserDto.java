package com.artur.callforpapers.domain.dto.papers;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {
    private String username;
    private String password;
}
