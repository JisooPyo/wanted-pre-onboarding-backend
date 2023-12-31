package com.recruitPageProject.jobPost.controller;

import com.recruitPageProject.common.dto.ApiResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostDeleteRequestDto;
import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import com.recruitPageProject.jobPost.dto.JobPostResponseDto;
import com.recruitPageProject.jobPost.service.JobPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@Operation(summary = "채용 공고 수정", description = "수정에 필요한 정보를 받아 채용 공고를 수정합니다")
	@PutMapping("/jobPost/{id}")
	public ResponseEntity<ApiResponseDto> updateJobPost(@Valid @RequestBody JobPostRequestDto requestDto, @PathVariable Long id) {
		jobPostService.updateJobPost(requestDto, id);
		return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "채용 공고 수정 완료"));
	}

	@Operation(summary = "채용 공고 삭제", description = "삭제에 필요한 정보를 받아 채용 공고를 삭제합니다")
	@DeleteMapping("/jobPost/{id}")
	public ResponseEntity<ApiResponseDto> deleteJobPost(@Valid @RequestBody JobPostDeleteRequestDto requestDto, @PathVariable Long id) {
		jobPostService.deleteJobPost(requestDto, id);
		return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "채용 공고 삭제 완료"));
	}

	@Operation(summary = "모든 채용 공고 조회", description = "모든 채용 공고를 조회합니다.")
	@GetMapping("/jobPosts/all")
	public ResponseEntity<List<JobPostFeedResponseDto>> getAllJobPosts() {
		List<JobPostFeedResponseDto> responseDtoList = jobPostService.getAllJobPosts();
		return ResponseEntity.ok().body(responseDtoList);
	}

	@Operation(summary = "채용 공고 상세 조회", description = "특정 채용 공고를 상세 조회합니다.")
	@GetMapping("/jobPost/{id}")
	public ResponseEntity<JobPostResponseDto> getJobPost(@PathVariable Long id) {
		JobPostResponseDto responseDto = jobPostService.getJobPost(id);
		return ResponseEntity.ok().body(responseDto);
	}

	@Operation(summary = "채용 공고 검색", description = "채용 공고를 검색합니다.")
	@GetMapping("/jobPosts")
	public ResponseEntity<List<JobPostFeedResponseDto>> searchJobPosts(@RequestParam String search){
		List<JobPostFeedResponseDto> responseDtoList = jobPostService.searchJobPosts(search);
		return ResponseEntity.ok().body(responseDtoList);
	}

}
