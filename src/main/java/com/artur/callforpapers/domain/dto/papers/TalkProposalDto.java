package com.artur.callforpapers.domain.dto.papers;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TalkProposalDto {
    private String title;

    private String resume;

    private String authorName;

    private String authorEmail;
}
