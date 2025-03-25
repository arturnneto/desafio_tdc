package com.artur.callforpapers;

import com.artur.callforpapers.domain.dto.papers.TalkProposalDto;
import com.artur.callforpapers.domain.entities.papers.TalkProposalEntity;

public final class TestsSupportClasses {

    private TestsSupportClasses() {

    }

    public static TalkProposalEntity createTalkProposal() {
        return TalkProposalEntity.builder()
                .id(1L)
                .title("Titulo A")
                .resume("Resumo A")
                .authorName("Nome A")
                .authorEmail("Email A")
                .build();
    }

    public static TalkProposalEntity createTalkProposalB() {
        return TalkProposalEntity.builder()
                .id(2L)
                .title("Titulo B")
                .resume("Resumo B")
                .authorName("Nome B")
                .authorEmail("Email B")
                .build();
    }

    public static TalkProposalEntity createTalkProposalC() {
        return TalkProposalEntity.builder()
                .id(3L)
                .title("Titulo C")
                .resume("Resumo C")
                .authorName("Nome C")
                .authorEmail("Email C")
                .build();
    }

    public static TalkProposalEntity createTalkProposalD() {
        return TalkProposalEntity.builder()
                .id(4L)
                .title("Titulo D")
                .resume("Resumo D")
                .authorName("Nome D")
                .authorEmail("Email D")
                .build();
    }

    public static TalkProposalEntity createTalkProposalE() {
        return TalkProposalEntity.builder()
                .id(5L)
                .title("Titulo E")
                .authorName("Nome E")
                .authorEmail("Email E")
                .build();
    }

//    public static TalkProposalDto createTalkProposalDto() {
//        return TalkProposalDto.builder()
//                .id(1L)
//                .title("Titulo E")
//                .authorName("Nome E")
//                .authorEmail("Email E")
//                .build();
//    }
}
