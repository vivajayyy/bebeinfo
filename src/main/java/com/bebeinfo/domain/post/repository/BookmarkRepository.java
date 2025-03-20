package com.bebeinfo.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bebeinfo.domain.post.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
