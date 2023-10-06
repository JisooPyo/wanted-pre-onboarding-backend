package com.recruitPageProject.apply.service;

import com.recruitPageProject.apply.dto.ApplyRequestDto;
import com.recruitPageProject.apply.entity.Apply;
import com.recruitPageProject.apply.repository.ApplyRepository;
import com.recruitPageProject.common.exception.CustomErrorCode;
import com.recruitPageProject.common.exception.CustomException;
import com.recruitPageProject.jobPost.entity.JobPost;
import com.recruitPageProject.jobPost.service.JobPostServiceImpl;
import com.recruitPageProject.user.entity.User;
import com.recruitPageProject.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplyServiceImpl implements ApplyService {
	private final ApplyRepository applyRepository;
	private final UserServiceImpl userService;
	private final JobPostServiceImpl jobPostService;


	@Override
	public void createApply(ApplyRequestDto requestDto) {
		Long jobPostId = requestDto.getJobPostId();
		Long userId = requestDto.getUserId();
		List<Apply> applyList = applyRepository.findApply(jobPostId, userId);
		if (applyList.size() != 0) {
			throw new CustomException(CustomErrorCode.ALREADY_APPLY);
		}
		User user = userService.findUserById(userId);
		JobPost jobPost = jobPostService.findJobPost(jobPostId);
		Apply apply = new Apply(user, jobPost);
		applyRepository.save(apply);
	}
}
