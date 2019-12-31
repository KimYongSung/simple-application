package com.kys.book.web;

import com.kys.book.service.posts.PostService;
import com.kys.book.web.dto.PostResponseDTO;
import com.kys.book.web.dto.PostSaveRequestDTO;
import com.kys.book.web.dto.PostUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDTO dto){
        return service.update(id, dto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }
}
