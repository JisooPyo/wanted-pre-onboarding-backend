package com.recruitPageProject.apply.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplyRequestDto {
	@NotNull
	Long jobPostId;
	@NotNull
	Long userId;
}
