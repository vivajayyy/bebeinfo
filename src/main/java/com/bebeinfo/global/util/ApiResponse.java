package com.bebeinfo.global.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {

		private boolean success;
		private String code;
		private String message;
		private T data;

		// 성공 응답 생성 (데이터 포함)
		public static <T> ApiResponse<T> success(T data) {
				return new ApiResponse<>(true, null, "요청이 성공적으로 처리되었습니다.", data);
		}

		// 성공 응답 생성 (코드, 메시지, 데이터 포함)
		public static <T> ApiResponse<T> success(String code, String message, T data) {
				return new ApiResponse<>(true, code, message, data);
		}

		// 성공 응답 생성 (메시지만 포함)
		public static <T> ApiResponse<T> success(String message) {
				return new ApiResponse<>(true, null, message, null);
		}

		// 실패 응답 생성 (코드, 메시지)
		public static <T> ApiResponse<T> error(String code, String message) {
				return new ApiResponse<>(false, code, message, null);
		}

		// 생성자
		private ApiResponse(boolean success, String code, String message, T data) {
				this.success = success;
				this.code = code;
				this.message = message;
				this.data = data;
		}
} 