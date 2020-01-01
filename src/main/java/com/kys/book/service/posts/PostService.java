package com.kys.book.service.posts;

import com.kys.book.domain.posts.Posts;
import com.kys.book.domain.posts.repository.PostRepository;
import com.kys.book.exception.UserNotFoundException;
import com.kys.book.web.dto.PostLIstResponseDTO;
import com.kys.book.web.dto.PostResponseDTO;
import com.kys.book.web.dto.PostSaveRequestDTO;
import com.kys.book.web.dto.PostUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    /**
     * post 등록
     * @param requestDTO
     * @return
     */
    public Long save(PostSaveRequestDTO requestDTO){
        return postRepository.save(requestDTO.toEntity()).getId();
    }

    /**
     * post 내용 수정
     * @param id
     * @param dto
     * @return
     */
    @Transactional
    public Long update(Long id, PostUpdateRequestDTO dto){

        Posts posts = postRepository.findById(id)
                                    .orElseThrow(UserNotFoundException::new);

        posts.update(dto.getTitle(), dto.getContent());

        return id;
    }

    /**
     * post 조회
     * @param id
     * @return
     */
    public PostResponseDTO findById(Long id){

        Posts posts = postRepository.findById(id)
                                    .orElseThrow(UserNotFoundException::new);

        return new PostResponseDTO(posts);
    }

    /**
     * 전체 포스트 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<PostLIstResponseDTO> findAllDesc(){
        return postRepository.findAllDesc()
                             .stream()
                             .map(PostLIstResponseDTO::new)
                             .collect(Collectors.toList());
    }
}
