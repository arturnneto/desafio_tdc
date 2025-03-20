package com.artur.callforpapers.mappers.impl;

import com.artur.callforpapers.domain.dto.papers.TalkProposalDto;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;
import com.artur.callforpapers.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TalkProposalMapperImpl implements Mapper<TalkProposalEntity, TalkProposalDto> {

    private ModelMapper modelMapper;

    public TalkProposalMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TalkProposalDto mapTo(TalkProposalEntity talkProposalEntity) {
        return modelMapper.map(talkProposalEntity, TalkProposalDto.class);
    }

    @Override
    public TalkProposalEntity mapFrom(TalkProposalDto talkProposalDto) {
        return modelMapper.map(talkProposalDto, TalkProposalEntity.class);
    }

}