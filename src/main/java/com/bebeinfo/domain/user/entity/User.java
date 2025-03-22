package com.bebeinfo.domain.user.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // 'user'는 예약어일 수 있어 'users'로 테이블명 지정
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column(nullable = false, unique = true)
		private String email;

		@Column(nullable = false)
		private String password;

		@Column(nullable = false, unique = true)
		private String nickname;

		private String profileImage;

		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		@Builder.Default
		private UserStatus status = UserStatus.ACTIVE;

		@ElementCollection(fetch = FetchType.EAGER)
		@Builder.Default
		private Set<UserRole> roles = new HashSet<>();

		@Column(nullable = false)
		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		private LocalDateTime lastLoginAt;

		private boolean emailVerified;

		// 시/도, 시/군/구, 상세지역
		private String sido;
		private String sigungu;
		private String detailRegion;

		// 관심 카테고리
		@ElementCollection
		@Builder.Default
		private Set<String> interests = new HashSet<>();

		@PrePersist
		protected void onCreate() {
				this.createdAt = LocalDateTime.now();
		}

		@PreUpdate
		protected void onUpdate() {
				this.updatedAt = LocalDateTime.now();
		}

		// 사용자 상태 업데이트
		public void updateStatus(UserStatus status) {
				this.status = status;
		}

		// 마지막 로그인 시간 업데이트
		public void updateLastLogin() {
				this.lastLoginAt = LocalDateTime.now();
		}

		// 이메일 인증 처리
		public void verifyEmail() {
				this.emailVerified = true;
		}

		// 프로필 정보 업데이트
		public void updateProfile(String nickname, String profileImage) {
				this.nickname = nickname;
				this.profileImage = profileImage;
		}

		// 지역 정보 업데이트
		public void updateRegion(String sido, String sigungu, String detailRegion) {
				this.sido = sido;
				this.sigungu = sigungu;
				this.detailRegion = detailRegion;
		}

		// 관심사 업데이트
		public void updateInterests(Set<String> interests) {
				this.interests = interests;
		}

		// 비밀번호 변경
		public void updatePassword(String encodedPassword) {
				this.password = encodedPassword;
		}

		// 권한 추가
		public void addRole(UserRole role) {
				this.roles.add(role);
		}

		// 권한 제거
		public void removeRole(UserRole role) {
				this.roles.remove(role);
		}
} 