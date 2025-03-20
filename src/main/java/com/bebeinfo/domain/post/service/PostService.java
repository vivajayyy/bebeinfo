package com.bebeinfo.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebeinfo.domain.post.entity.Post;
import com.bebeinfo.domain.post.repository.PostRepository;
import com.bebeinfo.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * 게시글 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 생성
     * 
     * @param post 저장할 게시글 엔티티
     * @return 저장된 게시글 엔티티
     */
    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * 게시글 수정
     * 
     * @param post 수정할 게시글 엔티티
     * @return 수정된 게시글 엔티티
     */
    @Transactional
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }
    
    /**
     * 게시글 삭제
     * 
     * @param post 삭제할 게시글 엔티티
     */
    @Transactional
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    /**
     * ID로 게시글 조회
     * 
     * @param postId 게시글 ID
     * @return 조회된 게시글 엔티티
     * @throws IllegalArgumentException ErrorCode.POST_NOT_FOUND 게시글이 존재하지 않을 경우
     */
    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.POST_NOT_FOUND.getMessage()));
    }

    /**
     * 모든 게시글 조회
     * 
     * @return 모든 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    /**
     * 사용자 ID로 게시글 조회
     * 
     * @param userId 사용자 ID
     * @return 해당 사용자가 작성한 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByAuthorId(userId);
    }

    /**
     * 카테고리로 게시글 조회
     * 
     * @param categoryId 카테고리 ID
     * @return 해당 카테고리의 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByCategory(Long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    /**
     * 게시판 ID로 게시글 조회
     * 
     * @param boardId 게시판 ID
     * @return 해당 게시판의 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByBoard(Long boardId) {
        return postRepository.findByBoardId(boardId);
    }

    /**
     * 태그로 게시글 조회
     * 
     * @param tag 검색할 태그
     * @return 해당 태그가 포함된 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByTag(String tag) {
        return postRepository.findByTagsContaining(tag);
    }

    /**
     * 제목으로 게시글 검색
     * 
     * @param title 검색할 제목 키워드
     * @return 제목에 키워드가 포함된 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }

    /**
     * 내용으로 게시글 검색
     * 
     * @param content 검색할 내용 키워드
     * @return 내용에 키워드가 포함된 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByContent(String content) {
        return postRepository.findByContentContaining(content);
    }

    /**
     * 게시글 조회수 증가
     * 
     * @param postId 조회수를 증가시킬 게시글 ID
     */
    @Transactional
    public void incrementViewCount(Long postId) {
        Post post = getPost(postId);
        post.incrementViewCount();
        postRepository.save(post);
    }
}
