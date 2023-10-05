package com.recruitPageProject.jobPost.controller;

import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import com.recruitPageProject.jobPost.service.JobPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "채용공고 API", description = "채용공고 CRUD를 위한 API 정보를 담고 있습니다")
public class JobPostController {
	private final JobPostService jobPostService;

	@Operation(summary = "채용 공고 등록", description = "등록에 필요한 정보를 받아 채용 공고를 등록합니다")
	@PostMapping("/jobPosts")
	public ResponseEntity<JobPostFeedResponseDto> createJobPost(@Valid @RequestBody JobPostRequestDto requestDto) {
		JobPostFeedResponseDto responseDto = jobPostService.createJobPost(requestDto);
		return ResponseEntity.ok().body(responseDto);
	}
}
