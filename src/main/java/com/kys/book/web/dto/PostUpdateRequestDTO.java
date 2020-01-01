package com.kys.book.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDTO {

    private String title;

    private String content;

    @Builder
    private PostUpdateRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
