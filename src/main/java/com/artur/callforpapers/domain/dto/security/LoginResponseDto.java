package com.artur.callforpapers.domain.dto.security;

public record LoginResponseDto(String accessToken, Long expiresIn) {
}
