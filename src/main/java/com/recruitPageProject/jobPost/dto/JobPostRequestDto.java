package com.recruitPageProject.jobPost.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JobPostRequestDto {
	@NotBlank
	Long companyId;
	@NotBlank
	String position;
	@NotBlank
	Long reward;
	@NotBlank
	String skill;
	@NotBlank
	String contents;

}
