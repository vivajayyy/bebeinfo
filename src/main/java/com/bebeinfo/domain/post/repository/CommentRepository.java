package com.bebeinfo.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bebeinfo.domain.post.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
