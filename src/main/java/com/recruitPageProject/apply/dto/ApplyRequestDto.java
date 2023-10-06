package com.recruitPageProject.apply.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ApplyRequestDto {
	@NotNull
	Long jobPostId;
	@NotNull
	Long userId;
}
