package com.recruitPageProject.jobPost.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobPostDeleteRequestDto {
	@NotNull
	Long companyId;
}

