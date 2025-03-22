package com.bebeinfo.domain.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "children")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Child {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id", nullable = false)
		private User user;

		@Column(nullable = false)
		private String name;

		@Column(nullable = false)
		private LocalDate birthDate;

		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		private Gender gender;

		@Column(nullable = false)
		private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		// 생성 시점에 호출
		@PrePersist
		protected void onCreate() {
				this.createdAt = LocalDateTime.now();
		}

		// 수정 시점에 호출
		@PreUpdate
		protected void onUpdate() {
				this.updatedAt = LocalDateTime.now();
		}

		// 월령 계산 (getter 메소드)
		@Transient // 데이터베이스에 저장되지 않는 필드
		public int getAgeInMonths() {
				if (birthDate == null) {
						return 0;
				}
				LocalDate now = LocalDate.now();
				Period period = Period.between(birthDate, now);
				return period.getYears() * 12 + period.getMonths();
		}

		// 데이터 업데이트 메소드
		public void update(String name, LocalDate birthDate, Gender gender) {
				this.name = name;
				this.birthDate = birthDate;
				this.gender = gender;
		}

		public static enum Gender {
				MALE, FEMALE, NONE
		}
} 