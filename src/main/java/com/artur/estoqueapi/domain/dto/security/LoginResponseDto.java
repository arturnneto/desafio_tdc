package com.artur.estoqueapi.domain.dto.security;

public record LoginResponseDto(String accessToken, Long expiresIn) {
}
