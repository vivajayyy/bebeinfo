package com.bebeinfo.domain.board.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boards")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column(nullable = false, unique = true)
		private String name;

		@Column(length = 1000)
		private String description;

		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		private AgeGroup ageGroup;

		@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
		@Builder.Default
		private List<BoardCategory> categories = new ArrayList<>();

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

		// 게시판 정보 업데이트
		public void update(String name, String description) {
				this.name = name;
				this.description = description;
		}

		// 카테고리 추가
		public void addCategory(BoardCategory category) {
				this.categories.add(category);
				category.setBoard(this);
		}

		// 카테고리 제거
		public void removeCategory(BoardCategory category) {
				this.categories.remove(category);
				category.setBoard(null);
		}

		// 월령 그룹 정의
		public enum AgeGroup {
				PREGNANCY("임신/출산"),
				NEWBORN("신생아(0-3개월)"),
				INFANT("영아(4-12개월)"),
				TODDLER("유아(13-24개월)"),
				CHILD_2_3("25-36개월"),
				CHILD_3_PLUS("36개월 이상");

				private final String displayName;

				AgeGroup(String displayName) {
						this.displayName = displayName;
				}

				public String getDisplayName() {
						return displayName;
				}
		}
} 