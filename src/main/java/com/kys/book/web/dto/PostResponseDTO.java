package com.kys.book.web.dto;

import com.kys.book.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponseDTO(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
