package com.bebeinfo.domain.post.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bebeinfo.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "comments")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "post_id", nullable = false)
		private Post post;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "parent_id")
		private Comment parent;

		@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
		@Builder.Default
		private List<Comment> replies = new ArrayList<>();

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id", nullable = false)
		private User author;

		@Column(nullable = false, columnDefinition = "TEXT")
		private String content;

		@Column(nullable = false)
		private long likeCount;

		@Column(nullable = false)
		private int depth;

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

		// 댓글 상태 변경
		public void updateStatus(Status status) {
				this.status = status;
		}

		// 댓글 내용 업데이트
		public void updateContent(String content) {
				this.content = content;
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

		// 답글 추가
		public void addReply(Comment reply) {
				this.replies.add(reply);
				reply.setParent(this);
		}

		// 부모 댓글 설정
		private void setParent(Comment parent) {
				this.parent = parent;
		}

		// 댓글 상태 열거형
		public enum Status {
				ACTIVE,       // 활성화
				DELETED,      // 삭제됨
				HIDDEN,       // 숨겨짐
				REPORTED      // 신고됨
		}
} 