package com.recruitPageProject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
	@NotBlank
	@Email
	String email;

	@NotBlank
	@Size(min = 4, message = "비밀번호는 4자 이상이어야 합니다.")
	String password;
}
