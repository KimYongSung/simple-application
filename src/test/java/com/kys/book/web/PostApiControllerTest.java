package com.kys.book.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kys.book.domain.posts.Posts;
import com.kys.book.domain.posts.repository.PostRepository;
import com.kys.book.web.dto.PostSaveRequestDTO;
import com.kys.book.web.dto.PostUpdateRequestDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                             .apply(springSecurity())
                             .build();
    }

    @After
    public void tearDown() throws Exception{
        postRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void post_등록() throws Exception{
        // given
        String title = "title";
        String content = "content";
        String author = "kys0213";

        PostSaveRequestDTO requestDTO = PostSaveRequestDTO.builder()
                                                          .title(title)
                                                          .content(content)
                                                          .author(author)
                                                          .build();

        String url = "http://localhost:" + port + "/api/v1/posts";


        // when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isOk());

        // then
        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void posts_수정() throws Exception{

        // given
        Posts savedPosts = postRepository.save(Posts.builder()
                                                    .title("title")
                                                    .content("content")
                                                    .author("author")
                                                    .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequestDTO requestDTO = PostUpdateRequestDTO.builder()
                                                              .title(expectedTitle)
                                                              .content(expectedContent)
                                                              .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostUpdateRequestDTO> requestEntity = new HttpEntity<>(requestDTO);

        // when
        mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isOk());

        // then
        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
