package com.recruitPageProject.user.service;

import com.recruitPageProject.user.dto.SignupRequestDto;

public interface UserService {
	/**
	 *
	 * @param requestDto (회원가입 필요 정보)
	 */
	void signup(SignupRequestDto requestDto);
}
