package com.artur.callforpapers.repositories.auth;

import com.artur.callforpapers.TestUtils;
import com.artur.callforpapers.domain.entities.auth.UserEntity;
import com.artur.callforpapers.repositories.UserRepository;
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
public class UserRepositoryIntegrationTests {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryIntegrationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testThatTalkProposalCanBeCreatedAndRetrieved() {
        UserEntity userEntity = TestUtils.createUserEntity();
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findById(userEntity.getUserId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userEntity);
    }

    @Test
    public void testThatMultipleTalkProposalCanBeCreatedAndRetrieved() {
        UserEntity userEntity = TestUtils.createUserEntity();
        userRepository.save(userEntity);
        UserEntity talkProposalEntityB = TestUtils.createUserEntityB();
        userRepository.save(talkProposalEntityB);
        UserEntity talkProposalEntityC = TestUtils.createUserEntityC();
        userRepository.save(talkProposalEntityC);

        Iterable<UserEntity> result = userRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(userEntity, talkProposalEntityB, talkProposalEntityC);
    }

    @Test
    public void testThatTalkProposalCanBeUpdated() {
        UserEntity userEntity = TestUtils.createUserEntity();
        userRepository.save(userEntity);
        userEntity.setUsername("UPDATED");
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findById(userEntity.getUserId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userEntity);
    }

    @Test
    public void testThatTalkProposalCanBeDeleted() {
        UserEntity userEntity = TestUtils.createUserEntity();
        userRepository.save(userEntity);
        userRepository.deleteById(userEntity.getUserId());
        Optional<UserEntity> result = userRepository.findById(userEntity.getUserId());
        assertThat(result).isEmpty();
    }
}
