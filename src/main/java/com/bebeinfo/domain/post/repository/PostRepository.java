package com.bebeinfo.domain.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bebeinfo.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);

    List<Post> findByContent(String content);

    List<Post> findByAuthorId(Long userId);

    List<Post> findByCategoryId(Long categoryId);

    List<Post> findByBoardId(Long boardId);

    List<Post> findByTagsContaining(String tag);

    List<Post> findByTitleContaining(String title);

    List<Post> findByContentContaining(String content);

}
