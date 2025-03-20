package com.bebeinfo.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bebeinfo.domain.post.entity.PostAttachment;

public interface PostAttachmentRepository extends JpaRepository<PostAttachment, Long> {

}
