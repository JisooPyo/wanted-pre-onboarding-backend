package com.recruitPageProject.jobPost.repository;

import com.recruitPageProject.jobPost.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long>, JobPostCustomRepository {
	List<JobPost> findAllByOrderByIdDesc();
}
