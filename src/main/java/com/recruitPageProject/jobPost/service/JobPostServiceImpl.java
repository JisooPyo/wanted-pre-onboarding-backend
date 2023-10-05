package com.recruitPageProject.jobPost.service;

import com.recruitPageProject.company.entity.Company;
import com.recruitPageProject.company.service.CompanyServiceImpl;
import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import com.recruitPageProject.jobPost.entity.JobPost;
import com.recruitPageProject.jobPost.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
