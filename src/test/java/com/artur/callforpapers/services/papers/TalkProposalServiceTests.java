package com.artur.callforpapers.services.papers;

import com.artur.callforpapers.TestUtils;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import com.artur.callforpapers.service.papers.TalkProposalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TalkProposalServiceTests {

    private TalkProposalService talkProposalService;

    @Autowired
    public TalkProposalServiceTests(TalkProposalService talkProposalService) {
        this.talkProposalService = talkProposalService;
    }

    @Test
    public void testThatCheckIfTalkProposalExistsReturnsHttp406IfTalkProposalExists() {
        TalkProposalEntity talkProposalEntity = TestUtils.createTalkProposal();
        talkProposalService.saveNewTalkProposal(talkProposalEntity);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            talkProposalService.checkIfTalkProposalExists(talkProposalEntity);
        });

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Proposal" + " " + talkProposalEntity.getTitle() + "Already exists on database."));
    }

    @Test
    public void testThatCheckIfTalkProposalExistsWithIdReturnsHttp404IfTalkProposalDoesNotExists() {
        TalkProposalEntity talkProposalEntity = TestUtils.createTalkProposal();
        talkProposalEntity.setId(99999999L);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            talkProposalService.checkIfTalkProposalExistsWithId(talkProposalEntity.getId());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Proposal not found on database."));
    }
}
