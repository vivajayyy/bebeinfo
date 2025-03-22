package com.bebeinfo.global.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
		// 공통 에러
		INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E001", "서버 내부 오류가 발생했습니다."),
		INVALID_REQUEST(HttpStatus.BAD_REQUEST, "E002", "유효하지 않은 요청입니다."),
		RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "E003", "요청한 리소스를 찾을 수 없습니다."),
		DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "E004", "이미 존재하는 리소스입니다."),
		ACCESS_DENIED(HttpStatus.FORBIDDEN, "E005", "접근 권한이 없습니다."),

		// 사용자 관련 에러
		USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "사용자를 찾을 수 없습니다."),
		EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "U002", "이미 존재하는 이메일입니다."),
		NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "U003", "이미 존재하는 닉네임입니다."),
		INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "U004", "비밀번호가 일치하지 않습니다."),
		UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "U005", "인증되지 않은 사용자입니다."),

		// 게시글 관련 에러
		POST_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "게시글을 찾을 수 없습니다."),
		INVALID_POST_CONTENT(HttpStatus.BAD_REQUEST, "P002", "게시글 내용이 유효하지 않습니다."),
		UNAUTHORIZED_POST_ACCESS(HttpStatus.FORBIDDEN, "P003", "게시글에 대한 접근 권한이 없습니다."),

		// 댓글 관련 에러
		COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "댓글을 찾을 수 없습니다."),
		INVALID_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "C002", "댓글 내용이 유효하지 않습니다."),
		UNAUTHORIZED_COMMENT_ACCESS(HttpStatus.FORBIDDEN, "C003", "댓글에 대한 접근 권한이 없습니다.");

		private final HttpStatus status;
		private final String code;
		private final String message;

		ErrorCode(HttpStatus status, String code, String message) {
				this.status = status;
				this.code = code;
				this.message = message;
		}

		public HttpStatus getStatus() {
				return status;
		}

		public String getCode() {
				return code;
		}

		public String getMessage() {
				return message;
		}
} 