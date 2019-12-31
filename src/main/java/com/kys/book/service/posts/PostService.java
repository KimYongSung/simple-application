package com.kys.book.service.posts;

import com.kys.book.domain.posts.Posts;
import com.kys.book.domain.posts.repository.PostRepository;
import com.kys.book.web.dto.PostResponseDTO;
import com.kys.book.web.dto.PostSaveRequestDTO;
import com.kys.book.web.dto.PostUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Transactional
    public Long update(Long id, PostUpdateRequestDTO dto){
        Posts posts = postRepository.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        posts.update(dto.getTitle(), dto.getContent());

        return id;
    }

    public PostResponseDTO findById(Long id){
        Posts posts = postRepository.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        return new PostResponseDTO(posts);
    }
}
