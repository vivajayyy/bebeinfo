package com.bebeinfo.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bebeinfo.domain.post.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
