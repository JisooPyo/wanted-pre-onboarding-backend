package com.recruitPageProject.jobPost.service;

import com.recruitPageProject.common.exception.CustomErrorCode;
import com.recruitPageProject.common.exception.CustomException;
import com.recruitPageProject.company.entity.Company;
import com.recruitPageProject.company.service.CompanyServiceImpl;
import com.recruitPageProject.jobPost.dto.JobPostDeleteRequestDto;
import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import com.recruitPageProject.jobPost.dto.JobPostResponseDto;
import com.recruitPageProject.jobPost.entity.JobPost;
import com.recruitPageProject.jobPost.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JobPostServiceImpl implements JobPostService {
	private final JobPostRepository jobPostRepository;
	private final CompanyServiceImpl companyService;

	@Override
	public JobPostFeedResponseDto createJobPost(JobPostRequestDto requestDto) {
		Long companyId = requestDto.getCompanyId();
		Company company = companyService.findCompany(companyId);
		JobPost jobPost = new JobPost(company, requestDto);
		jobPostRepository.save(jobPost);
		return new JobPostFeedResponseDto(jobPost);
	}

	@Override
	public void updateJobPost(JobPostRequestDto requestDto, Long id) {
		Long companyId = requestDto.getCompanyId();
		JobPost jobPost = findJobPost(id);
		if (!jobPost.getCompany().getId().equals(companyId)) {
			throw new CustomException(CustomErrorCode.NO_AUTHORIZATION);
		}
		jobPost.update(requestDto);
	}

	@Override
	public void deleteJobPost(JobPostDeleteRequestDto requestDto, Long id) {
		Long companyId = requestDto.getCompanyId();
		JobPost jobPost = findJobPost(id);
		if (!jobPost.getCompany().getId().equals(companyId)) {
			throw new CustomException(CustomErrorCode.NO_AUTHORIZATION);
		}
		jobPostRepository.deleteById(id);
	}

	@Override
	public List<JobPostFeedResponseDto> getAllJobPosts() {
		List<JobPost> jobPostList = jobPostRepository.findAllByOrderByIdDesc();
		return jobPostList.stream().map(JobPostFeedResponseDto::new).toList();
	}

	@Override
	public JobPostResponseDto getJobPost(Long id) {
		JobPost jobPost = findJobPost(id);
		JobPostResponseDto responseDto = new JobPostResponseDto(jobPost);
		List<JobPost> otherJobPosts = jobPostRepository.findOtherJobPosts(id);
		List<Long> otherJobPostsIdList = new ArrayList<>();
		for(JobPost jp : otherJobPosts){
			otherJobPostsIdList.add(jp.getId());
		}
		responseDto.addOtherJobPosts(otherJobPostsIdList);
		return responseDto;
	}

	private JobPost findJobPost(Long id) {
		return jobPostRepository.findById(id).orElseThrow(() ->
				new CustomException(CustomErrorCode.JOBPOST_NOT_FOUND));
	}

}
