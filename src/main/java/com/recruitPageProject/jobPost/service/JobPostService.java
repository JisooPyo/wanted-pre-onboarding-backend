package com.recruitPageProject.jobPost.service;

import com.recruitPageProject.jobPost.dto.JobPostDeleteRequestDto;
import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;

public interface JobPostService {
	/**
	 * @param requestDto (채용 공고 등록 정보)
	 * @return JobPostFeedResponseDto (Feed에서 보여지는 채용 공고 정보)
	 */
	JobPostFeedResponseDto createJobPost(JobPostRequestDto requestDto);

	/**
	 *
	 * @param requestDto (채용 공고 수정 내용)
	 * @param id (수정할 채용 공고 id)
	 */
	void updateJobPost(JobPostRequestDto requestDto, Long id);

	/**
	 *
	 * @param requestDto (채용 공고 삭제 필요 정보)
	 * @param id (삭제할 채용 공고 id)
	 */
	void deleteJobPost(JobPostDeleteRequestDto requestDto, Long id);
}
