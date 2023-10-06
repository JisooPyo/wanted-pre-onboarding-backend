package com.recruitPageProject.jobPost.repository;

import com.recruitPageProject.jobPost.entity.JobPost;

import java.util.List;

public interface JobPostCustomRepository {
	List<JobPost> findOtherJobPosts(Long id);

	List<JobPost> searchJobPosts(String search);
}
