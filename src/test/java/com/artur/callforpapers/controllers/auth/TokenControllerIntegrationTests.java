package com.artur.callforpapers.controllers.auth;

import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.service.papers.UserService;
import com.artur.callforpapers.service.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TokenControllerIntegrationTests {

    private TokenService tokenService;
    @Autowired
    private UserService userService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public TokenControllerIntegrationTests(TokenService tokenService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.tokenService = tokenService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCorrectCredentialsReturnsHttpStatus200Ok() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .username("Username")
                .password("Password")
                .build();

        userService.saveNewUser(userEntity);

        LoginRequestDto loginRequestDto = new LoginRequestDto("Username", "Password");
        String loginRequestDtoJson = objectMapper.writeValueAsString(loginRequestDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isUnauthorized()
        );
    }

    @Test
    public void testThatIncorrectCredentialsReturnsHttpStatus401Unauthorized() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto("Username", "Password");
        String loginRequestDtoJson = objectMapper.writeValueAsString(loginRequestDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isUnauthorized()
        );
    }
}
