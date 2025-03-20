package com.artur.callforpapers.service.papers;

import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;

import java.util.Optional;

public interface TalkProposalService {

    void checkIfTalkProposalExists(TalkProposalEntity talkProposalEntity);

    TalkProposalEntity createNewTalkProposal(TalkProposalEntity talkProposalEntity);

    TalkProposalEntity saveNewTalkProposal(TalkProposalEntity talkProposalEntity);

    Optional<TalkProposalEntity> getTalkProposal(TalkProposalEntity talkProposalEntity);

    void deleteTalkProposal(Long id);
}
