package com.bebeinfo.domain.user.entity;

public enum UserStatus {
    ACTIVE,       // 활성 계정
    INACTIVE,     // 비활성 계정
    SUSPENDED,    // 일시 정지된 계정
    BANNED,       // 영구 차단된 계정
    PENDING       // 인증 대기 중인 계정
} 