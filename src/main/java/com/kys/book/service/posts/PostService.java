package com.kys.book.service.posts;

import com.kys.book.domain.posts.repository.PostRepository;
import com.kys.book.web.dto.PostSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    /**
     *
     * @param requestDTO
     * @return
     */
    public Long save(PostSaveRequestDTO requestDTO){
        return postRepository.save(requestDTO.toEntity()).getId();
    }
}
