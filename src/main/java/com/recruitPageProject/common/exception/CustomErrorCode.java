package com.recruitPageProject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
	//	USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다."),
	COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회사입니다."),
	JOBPOST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 채용공고입니다."),
	NO_AUTHORIZATION(HttpStatus.UNAUTHORIZED.value(), "권한이 없습니다.");

	private final int errorCode;
	private final String errorMessage;
}
