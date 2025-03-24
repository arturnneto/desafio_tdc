package com.artur.callforpapers.controllers.papers;

import com.artur.callforpapers.domain.dto.papers.TalkProposalDto;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import com.artur.callforpapers.mappers.Mapper;
import com.artur.callforpapers.service.papers.TalkProposalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class TalkProposalController {


    @Autowired
    private TalkProposalService talkProposalService;
    @Autowired
    private Mapper<TalkProposalEntity, TalkProposalDto> talkProposalMapper;

    @Transactional
    @PostMapping("/talk-proposal")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<TalkProposalDto> createNewTalkProposal(@RequestBody TalkProposalDto talkProposalDto) {
        TalkProposalEntity talkProposalFromDatabase = talkProposalMapper.mapFrom(talkProposalDto);
        TalkProposalEntity newTalkProposal = talkProposalService.createNewTalkProposal(talkProposalFromDatabase);
        talkProposalService.saveNewTalkProposal(newTalkProposal);

        return new ResponseEntity<>(talkProposalMapper.mapTo(newTalkProposal), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/talk-proposal/{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity deleteTalkProposal(@PathVariable("id") Long id) {
        talkProposalService.deleteTalkProposal(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @PutMapping("/talk-proposal/{id}")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<TalkProposalDto> updateTalkProposal(@PathVariable("id") Long id, @RequestBody TalkProposalDto talkProposalDto) {
        talkProposalService.checkIfTalkProposalExists(id);
        TalkProposalEntity talkProposalFromDatabase = talkProposalMapper.mapFrom(talkProposalDto);
        TalkProposalEntity updatedTalkProposal = talkProposalService.updateTalkProposal(id, talkProposalFromDatabase);

        return new ResponseEntity<>(talkProposalMapper.mapTo(updatedTalkProposal), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/talk-proposal/{id}")
    public ResponseEntity<TalkProposalDto> getTalkProposal(@PathVariable("id") Long id) {
        talkProposalService.checkIfTalkProposalExists(id);
        Optional<TalkProposalEntity> proposalFromDatabase = talkProposalService.getTalkProposal(id);
        return new ResponseEntity<>(talkProposalMapper.mapTo(proposalFromDatabase.get()), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/talk-proposal")
    public ResponseEntity<List<TalkProposalDto>> getAllTalkProposals() {
        List<TalkProposalEntity> allTalkProposals = talkProposalService.getAllTalkProposals();
        return new ResponseEntity<>(allTalkProposals.stream().map(talkProposalMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }
}
