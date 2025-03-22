package com.bebeinfo.domain.post.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bebeinfo.domain.board.entity.Board;
import com.bebeinfo.domain.board.entity.BoardCategory;
import com.bebeinfo.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "board_id", nullable = false)
		private Board board;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "category_id")
		private BoardCategory category;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id", nullable = false)
		private User author;

		@Column(nullable = false)
		private String title;

		@Column(nullable = false, columnDefinition = "TEXT")
		private String content;

		@Column(nullable = false)
		private long viewCount;

		@Column(nullable = false)
		private long likeCount;

		@Column(nullable = false)
		private long commentCount;

		@ElementCollection
		@Builder.Default
		private Set<String> tags = new HashSet<>();

		@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
		@Builder.Default
		private List<PostImage> images = new ArrayList<>();

		@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
		@Builder.Default
		private List<PostAttachment> attachments = new ArrayList<>();

		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		@Builder.Default
		private Visibility visibility = Visibility.PUBLIC;

		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		@Builder.Default
		private Status status = Status.ACTIVE;

		@Column(nullable = false)
		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		@PrePersist
		protected void onCreate() {
				this.createdAt = LocalDateTime.now();
		}

		@PreUpdate
		protected void onUpdate() {
				this.updatedAt = LocalDateTime.now();
		}

		// 게시글 상태 변경
		public void updateStatus(Status status) {
				this.status = status;
		}

		// 게시글 내용 업데이트
		public void updateContent(String title, String content, BoardCategory category,
				Set<String> tags, Visibility visibility) {
				this.title = title;
				this.content = content;
				this.category = category;
				this.tags = tags;
				this.visibility = visibility;
		}

		// 조회수 증가
		public void incrementViewCount() {
				this.viewCount++;
		}

		// 좋아요 수 증가
		public void incrementLikeCount() {
				this.likeCount++;
		}

		// 좋아요 수 감소
		public void decrementLikeCount() {
				if (this.likeCount > 0) {
						this.likeCount--;
				}
		}

		// 댓글 수 증가
		public void incrementCommentCount() {
				this.commentCount++;
		}

		// 댓글 수 감소
		public void decrementCommentCount() {
				if (this.commentCount > 0) {
						this.commentCount--;
				}
		}

		// 이미지 추가
		public void addImage(PostImage image) {
				this.images.add(image);
				image.setPost(this);
		}

		// 이미지 제거
		public void removeImage(PostImage image) {
				this.images.remove(image);
				image.setPost(null);
		}

		// 첨부파일 추가
		public void addAttachment(PostAttachment attachment) {
				this.attachments.add(attachment);
				attachment.setPost(this);
		}

		// 첨부파일 제거
		public void removeAttachment(PostAttachment attachment) {
				this.attachments.remove(attachment);
				attachment.setPost(null);
		}

		// 공개 범위 열거형
		public enum Visibility {
				PUBLIC,    // 전체 공개
				FRIENDS,   // 친구에게만 공개
				PRIVATE    // 나만 보기
		}

		// 게시글 상태 열거형
		public enum Status {
				ACTIVE,       // 활성화
				DELETED,      // 삭제됨
				HIDDEN,       // 숨겨짐
				REPORTED      // 신고됨
		}
} 