package com.bebeinfo.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bebeinfo.domain.post.entity.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

}
