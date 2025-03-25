package com.artur.callforpapers.services.auth;

import com.artur.callforpapers.TestUtils;
import com.artur.callforpapers.domain.dto.security.LoginRequestDto;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import com.artur.callforpapers.service.security.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TokenServiceTests {

    private TokenService tokenService;

    @Autowired
    public TokenServiceTests(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Test
    public void testThatCheckIfLoginIsCorrectThrowsBadCredentialExcpetionIfUserLoginIsIncorrect() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("Username", "Password");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            tokenService.checkIfLoginIsCorrect(loginRequestDto, bCryptPasswordEncoder);
        });

        assertEquals("User or password is not valid.", exception.getMessage());
    }
}
