package com.kys.book.web.dto;

import com.kys.book.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDTO {

    private String title;
    private String content;
    private String author;

    @Builder
    private PostSaveRequestDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
    }
}
