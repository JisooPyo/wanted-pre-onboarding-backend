package com.recruitPageProject.jobPost.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class JobPostDeleteRequestDto {
	@NotNull
	Long companyId;
}

