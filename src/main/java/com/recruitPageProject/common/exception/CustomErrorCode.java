package com.recruitPageProject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다.");

	private final int errorCode;
	private final String errorMessage;
}
