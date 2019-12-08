package com.kys.book.web;

import com.kys.book.service.posts.PostService;
import com.kys.book.web.dto.PostSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostService service;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDTO dto){
        return service.save(dto);
    }
}
