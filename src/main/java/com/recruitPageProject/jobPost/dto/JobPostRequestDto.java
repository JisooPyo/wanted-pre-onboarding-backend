package com.recruitPageProject.jobPost.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class JobPostRequestDto {
	@NotNull
	Long companyId;
	@NotBlank
	String position;
	@NotNull
	Long reward;
	@NotBlank
	String skill;
	@NotBlank
	String contents;

}
