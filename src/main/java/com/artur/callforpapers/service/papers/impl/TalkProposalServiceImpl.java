package com.artur.callforpapers.service.papers.impl;

import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import com.artur.callforpapers.repositories.TalkProposalRepository;
import com.artur.callforpapers.service.papers.TalkProposalService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TalkProposalServiceImpl implements TalkProposalService {

    @Autowired
    private TalkProposalRepository talkProposalRepository;

    @Override
    public Optional<TalkProposalEntity> getTalkProposal(TalkProposalEntity talkProposalEntity) {
        return talkProposalRepository.findByTitle(talkProposalEntity.getTitle());
    }

    @Override
    public TalkProposalEntity saveNewTalkProposal(TalkProposalEntity talkProposalEntity) {
        return talkProposalRepository.save(talkProposalEntity);
    }

    @Override
    public void checkIfTalkProposalExists(TalkProposalEntity talkProposalEntity) {
        Optional<TalkProposalEntity> talkProposalFromDatabase = getTalkProposal(talkProposalEntity);

        if (talkProposalFromDatabase.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Proposal" + " " + talkProposalEntity.getTitle() + "Already exists on database.");
        }
    }

    @Override
    public TalkProposalEntity createNewTalkProposal(TalkProposalEntity talkProposalEntity) {
        checkIfTalkProposalExists(talkProposalEntity);

        TalkProposalEntity newTalkProposal = new TalkProposalEntity();
        newTalkProposal.setTitle(talkProposalEntity.getTitle());

        return newTalkProposal;
    }
}
