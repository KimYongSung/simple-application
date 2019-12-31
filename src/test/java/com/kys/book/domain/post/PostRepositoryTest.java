package com.kys.book.domain.post;

import com.kys.book.domain.posts.Posts;
import com.kys.book.domain.posts.repository.PostRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @After
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "dydtjd0245@gmail.com";

        postRepository.save(Posts.builder()
                                 .title(title)
                                 .content(content)
                                 .author(author)
                                 .build());
        // when
        List<Posts> postsList = postRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntity_등록(){

        // given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);

        postRepository.save(Posts.builder()
                                 .title("title")
                                 .content("content")
                                 .author("author")
                                 .build());

        // when
        List<Posts> postsList = postRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        System.out.println(posts.toString());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
