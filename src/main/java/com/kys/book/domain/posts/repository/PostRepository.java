package com.kys.book.domain.posts.repository;

import com.kys.book.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
