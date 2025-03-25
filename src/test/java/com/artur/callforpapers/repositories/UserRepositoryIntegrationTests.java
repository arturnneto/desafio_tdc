package com.artur.callforpapers.repositories;

import com.artur.callforpapers.TestUtils;
import com.artur.callforpapers.domain.entities.auth.RoleEntity;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class RoleRepositoryIntegrationTests {

    private RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryIntegrationTests(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    public void testThatTalkProposalCanBeCreatedAndRetrieved() {
        RoleEntity roleEntity = TestUtils.createTalkProposal();
        roleRepository.save(RoleEntity);
        Optional<TalkProposalEntity> result = roleRepository.findById(roleEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(talkProposalEntity);
    }

    @Test
    public void testThatMultipleTalkProposalCanBeCreatedAndRetrieved() {
        RoleEntity roleEntity = TestUtils.createTalkProposal();
        roleRepository.save(roleEntity);
        RoleEntity talkProposalEntityB = TestUtils.createTalkProposalB();
        roleRepository.save(talkProposalEntityB);
        RoleEntity talkProposalEntityC = TestUtils.createTalkProposalC();
        roleRepository.save(talkProposalEntityC);

        Iterable<RoleEntity> result = roleRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(talkProposalEntity, talkProposalEntityB, talkProposalEntityC);
    }

    @Test
    public void testThatTalkProposalCanBeUpdated() {
        RoleEntity roleEntity = TestUtils.createTalkProposal();
        roleRepository.save(talkProposalEntity);
        roleEntity.setResume("UPDATED");
        roleRepository.save(roleEntity);
        Optional<RoleEntity> result = roleRepository.findById(roleEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(roleEntity);
    }

    @Test
    public void testThatTalkProposalCanBeDeleted() {
        RoleEntity roleEntity = TestUtils.createTalkProposal();
        roleRepository.save(roleEntity);
        roleRepository.deleteById(roleEntity.getId());
        Optional<RoleEntity> result = roleRepository.findById(roleEntity.getId());
        assertThat(result).isEmpty();
    }
}
