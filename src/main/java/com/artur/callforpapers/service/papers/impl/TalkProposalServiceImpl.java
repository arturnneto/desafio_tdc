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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public Optional<TalkProposalEntity> getTalkProposal(Long id) {
        return talkProposalRepository.findById(id);
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
    public void checkIfTalkProposalExistsWithId(Long id) {
        Optional<TalkProposalEntity> talkProposalFromDatabase = getTalkProposal(id);

        if (talkProposalFromDatabase.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposal not found on database.");
        }
    }

    @Override
    public TalkProposalEntity createNewTalkProposal(TalkProposalEntity talkProposalEntity) {
        checkIfTalkProposalExists(talkProposalEntity);

        TalkProposalEntity newTalkProposal = new TalkProposalEntity();
        newTalkProposal.setTitle(talkProposalEntity.getTitle());
        newTalkProposal.setResume(talkProposalEntity.getResume());
        newTalkProposal.setAuthorName(talkProposalEntity.getAuthorName());
        newTalkProposal.setAuthorEmail(talkProposalEntity.getAuthorEmail());

        return newTalkProposal;
    }

    @Override
    public void deleteTalkProposal(Long id) {
        talkProposalRepository.deleteById(id);
    }

    @Override
    public TalkProposalEntity updateTalkProposal(Long id, TalkProposalEntity talkProposalEntity) {
        talkProposalEntity.setId(id);

        return getTalkProposal(id).map(existingTalkProposal -> {
            Optional.ofNullable(talkProposalEntity.getTitle()).ifPresent(existingTalkProposal::setTitle);
            Optional.ofNullable(talkProposalEntity.getResume()).ifPresent(existingTalkProposal::setResume);
            Optional.ofNullable(talkProposalEntity.getAuthorName()).ifPresent(existingTalkProposal::setAuthorName);
            Optional.ofNullable(talkProposalEntity.getAuthorEmail()).ifPresent(existingTalkProposal::setAuthorEmail);
            return talkProposalRepository.save(existingTalkProposal);
        }).orElseThrow(() -> new RuntimeException("Talk proposal does not exists."));
    }

    @Override
    public List<TalkProposalEntity> getAllTalkProposals() {
        return new ArrayList<>(talkProposalRepository.findAll());
    }
}
