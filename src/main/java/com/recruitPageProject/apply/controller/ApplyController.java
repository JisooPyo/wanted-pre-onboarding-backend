package com.recruitPageProject.apply.controller;

import com.recruitPageProject.apply.dto.ApplyRequestDto;
import com.recruitPageProject.apply.service.ApplyService;
import com.recruitPageProject.common.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "지원 API", description = "채용공고 지원에 대한 API 정보를 담고 있습니다")
public class ApplyController {
	private final ApplyService applyService;

	@Operation(summary = "채용 공고 지원", description = "채용 공고에 지원합니다")
	@PostMapping("/apply")
	public ResponseEntity<ApiResponseDto> createApply(@Valid @RequestBody ApplyRequestDto requestDto) {
		applyService.createApply(requestDto);
		return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "채용 공고 지원 완료"));
	}
}
