package com.artur.callforpapers.domain.dto.papers;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TalkProposalDto {
    @NotBlank
    private String title;
    @NotBlank
    private String resume;
    @NotBlank
    private String authorName;
    @NotBlank
    private String authorEmail;
}
