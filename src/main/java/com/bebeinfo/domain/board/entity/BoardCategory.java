package com.bebeinfo.domain.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "board_categories")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategory {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "board_id", nullable = false)
		@Setter // Board 클래스에서 양방향 관계 설정을 위해 필요
		private Board board;

		@Column(nullable = false)
		private String name;

		@Column(nullable = false)
		private int orderNum;  // 카테고리 정렬 순서

		@Column(nullable = false)
		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		// 게시글 수 (캐시)
		private long postCount;

		@PrePersist
		protected void onCreate() {
				this.createdAt = LocalDateTime.now();
		}

		@PreUpdate
		protected void onUpdate() {
				this.updatedAt = LocalDateTime.now();
		}

		// 게시글 수 업데이트
		public void updatePostCount(long count) {
				this.postCount = count;
		}

		// 게시글 수 증가
		public void incrementPostCount() {
				this.postCount++;
		}

		// 게시글 수 감소
		public void decrementPostCount() {
				if (this.postCount > 0) {
						this.postCount--;
				}
		}

		// 카테고리 정보 업데이트
		public void update(String name, int orderNum) {
				this.name = name;
				this.orderNum = orderNum;
		}
} 