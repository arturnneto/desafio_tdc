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
}
