package com.artur.callforpapers;

import com.artur.callforpapers.domain.dto.papers.TalkProposalDto;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@DirtiesContext
@ActiveProfiles("test")
public final class TestUtils {

    private TestUtils() {

    }

    public static TalkProposalEntity createTalkProposal() {
        return TalkProposalEntity.builder()
                .title("Titulo A")
                .resume("Resumo A")
                .authorName("Nome A")
                .authorEmail("Email A")
                .build();
    }

    public static TalkProposalEntity createTalkProposalB() {
        return TalkProposalEntity.builder()
                .title("Titulo B")
                .resume("Resumo B")
                .authorName("Nome B")
                .authorEmail("Email B")
                .build();
    }

    public static TalkProposalEntity createTalkProposalC() {
        return TalkProposalEntity.builder()
                .title("Titulo C")
                .resume("Resumo C")
                .authorName("Nome C")
                .authorEmail("Email C")
                .build();
    }

    public static UserEntity createUserEntity() {
        return UserEntity.builder()
                .username("Username")
                .password("Password")
                .build();
    }

    public static UserEntity createUserEntityB() {
        return UserEntity.builder()
                .username("Username B")
                .password("Password B")
                .build();
    }

    public static UserEntity createUserEntityC() {
        return UserEntity.builder()
                .username("Username C")
                .password("Password C")
                .build();
    }

    public static TalkProposalDto createTalkProposalDto(String title, String resume, String authorName, String authorEmail) {
        TalkProposalDto testTalkProposal = new TalkProposalDto();
        testTalkProposal.setTitle(title);
        testTalkProposal.setResume(resume);
        testTalkProposal.setAuthorName(authorName);
        testTalkProposal.setAuthorEmail(authorEmail);

        return testTalkProposal;
    }

    public static String generateToken(MockMvc mockMvc) throws Exception {
        String body = "{\n    \"username\": \"admin\",\n    \"password\": \"123\"\n}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String token = response.substring(response.indexOf(":\"") + 2, response.indexOf("\","));
        return token;
    }
}
