package com.artur.callforpapers.repositories.papers;

import com.artur.callforpapers.TestUtils;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.artur.callforpapers.repositories.papers")
public class TalkProposalRepositoryIntegrationTests {

    private TalkProposalRepository talkProposalRepository;

    @Autowired
    public TalkProposalRepositoryIntegrationTests(TalkProposalRepository talkProposalRepository) {
        this.talkProposalRepository = talkProposalRepository;
    }

    @Test
    public void testThatTalkProposalCanBeCreatedAndRetrieved() {
        TalkProposalEntity talkProposalEntity = TestUtils.createTalkProposal();
        talkProposalRepository.save(talkProposalEntity);
        Optional<TalkProposalEntity> result = talkProposalRepository.findById(talkProposalEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(talkProposalEntity);
    }

    @Test
    public void testThatMultipleTalkProposalCanBeCreatedAndRetrieved() {
        TalkProposalEntity talkProposalEntity = TestUtils.createTalkProposal();
        talkProposalRepository.save(talkProposalEntity);
        TalkProposalEntity talkProposalEntityB = TestUtils.createTalkProposalB();
        talkProposalRepository.save(talkProposalEntityB);
        TalkProposalEntity talkProposalEntityC = TestUtils.createTalkProposalC();
        talkProposalRepository.save(talkProposalEntityC);

        Iterable<TalkProposalEntity> result = talkProposalRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(talkProposalEntity, talkProposalEntityB, talkProposalEntityC);
    }

    @Test
    public void testThatTalkProposalCanBeUpdated() {
        TalkProposalEntity talkProposalEntity = TestUtils.createTalkProposal();
        talkProposalRepository.save(talkProposalEntity);
        talkProposalEntity.setResume("UPDATED");
        talkProposalRepository.save(talkProposalEntity);
        Optional<TalkProposalEntity> result = talkProposalRepository.findById(talkProposalEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(talkProposalEntity);
    }

    @Test
    public void testThatTalkProposalCanBeDeleted() {
        TalkProposalEntity talkProposalEntity = TestUtils.createTalkProposal();
        talkProposalRepository.save(talkProposalEntity);
        talkProposalRepository.deleteById(talkProposalEntity.getId());
        Optional<TalkProposalEntity> result = talkProposalRepository.findById(talkProposalEntity.getId());
        assertThat(result).isEmpty();
    }
}
