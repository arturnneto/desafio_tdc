package com.artur.callforpapers.controllers;

import com.artur.callforpapers.TestUtils;
import com.artur.callforpapers.domain.dto.papers.TalkProposalDto;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import com.artur.callforpapers.service.papers.TalkProposalService;
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
public class TalkProposalControllerIntegrationTests {

    private TalkProposalService talkProposalService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public TalkProposalControllerIntegrationTests(TalkProposalService talkProposalService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.talkProposalService = talkProposalService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateTalkProposalReturnsHttp201Created() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        TalkProposalDto testTalkProposal = TestUtils.createTalkProposalDto("Title", "Resume", "Name", "Email");
        String talkProposalJson = objectMapper.writeValueAsString(testTalkProposal);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/talk-proposal")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(talkProposalJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateTalkProposalReturnsCreatedTalkProposal() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        TalkProposalDto testTalkProposal = TestUtils.createTalkProposalDto("Title", "Resume", "Name", "Email");
        String talkProposalJson = objectMapper.writeValueAsString(testTalkProposal);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/talk-proposal")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(talkProposalJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testTalkProposal.getTitle()) // Expected: Title
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.authorName").value(testTalkProposal.getAuthorName()) // Expected: Name
        );
    }

    @Test
    public void testThatGetOneTalkProposalReturnsHttp200Ok() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        TalkProposalEntity testTalkProposal = TestUtils.createTalkProposal();
        talkProposalService.saveNewTalkProposal(testTalkProposal);
        String id = testTalkProposal.getId().toString();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/talk-proposal/" + id)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThaGetOneTalkProposalReturnsCorrectTalkProposal() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        TalkProposalEntity testTalkProposal = TestUtils.createTalkProposal();
        talkProposalService.saveNewTalkProposal(testTalkProposal);
        String id = testTalkProposal.getId().toString();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/talk-proposal/" + id)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testTalkProposal.getTitle()) // Expected: Titulo A
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.authorName").value(testTalkProposal.getAuthorName()) // Expected: Nome A
        );
    }

    @Test
    public void testThatGetAllTalkProposalsReturnsHttp200Ok() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/talk-proposal")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAllTalkProposalsReturnsCorrectTalkProposals() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        TalkProposalEntity testTalkProposal = TestUtils.createTalkProposal();
        TalkProposalEntity testTalkProposalB = TestUtils.createTalkProposalB();
        TalkProposalEntity testTalkProposalC = TestUtils.createTalkProposalC();
        talkProposalService.saveNewTalkProposal(testTalkProposal);
        talkProposalService.saveNewTalkProposal(testTalkProposalB);
        talkProposalService.saveNewTalkProposal(testTalkProposalC);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/talk-proposal")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(testTalkProposal.getTitle()) // Expected: Titulo A
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value(testTalkProposalB.getTitle()) // Expected: Titulo B
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2].title").value(testTalkProposalC.getTitle()) // Expected: Titulo C
        );
    }

    @Test
    public void testThatUpdateTalkProposalReturnsHttp200Ok() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        TalkProposalEntity testTalkProposal = TestUtils.createTalkProposal();
        talkProposalService.saveNewTalkProposal(testTalkProposal);
        String id = testTalkProposal.getId().toString();

        TalkProposalDto testTalkProposalDto = TestUtils.createTalkProposalDto("Title", "Resume", "Name", "Email");
        String talkProposalJson = objectMapper.writeValueAsString(testTalkProposal);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/talk-proposal/" + id)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(talkProposalJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateTalkProposalSuccessfullyUpdatesTalkProposal() throws Exception {
        String token = TestUtils.generateToken(mockMvc);

        TalkProposalEntity testTalkProposal = TestUtils.createTalkProposal();
        talkProposalService.saveNewTalkProposal(testTalkProposal);
        String id = testTalkProposal.getId().toString();

        TalkProposalDto testTalkProposalDto = TestUtils.createTalkProposalDto("Title", "Resume", "Name", "Email");
        String talkProposalJson = objectMapper.writeValueAsString(testTalkProposal);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/talk-proposal/" + id)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(talkProposalJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testTalkProposal.getTitle()) // Expected: Title
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.resume").value(testTalkProposal.getResume()) // Expected: Resume
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.authorName").value(testTalkProposal.getAuthorName()) // Expected: Name
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.authorEmail").value(testTalkProposal.getAuthorEmail()) // Expected: Email
        );
    }
}
