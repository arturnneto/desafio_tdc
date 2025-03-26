package com.artur.callforpapers.repositories.papers;

import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TalkProposalRepository extends JpaRepository<TalkProposalEntity, Long> {
    Optional<TalkProposalEntity> findByTitle(String title);

    void deleteById(Long id);
}
