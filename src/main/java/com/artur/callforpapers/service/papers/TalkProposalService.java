package com.artur.callforpapers.service.papers;

import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;

import java.util.List;
import java.util.Optional;

public interface TalkProposalService {

    void checkIfTalkProposalExists(TalkProposalEntity talkProposalEntity);

    void checkIfTalkProposalExists(Long id);

    TalkProposalEntity createNewTalkProposal(TalkProposalEntity talkProposalEntity);

    TalkProposalEntity saveNewTalkProposal(TalkProposalEntity talkProposalEntity);

    Optional<TalkProposalEntity> getTalkProposal(TalkProposalEntity talkProposalEntity);

    Optional<TalkProposalEntity> getTalkProposal(Long Id);

    TalkProposalEntity updateTalkProposal(Long id, TalkProposalEntity talkProposalEntity);

    void deleteTalkProposal(Long id);

    List<TalkProposalEntity> getAllTalkProposals();
}
