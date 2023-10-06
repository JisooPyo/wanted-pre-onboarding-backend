package com.recruitPageProject.apply.repository;

import com.recruitPageProject.apply.entity.Apply;

import java.util.List;

public interface ApplyCustomRepository {
	List<Apply> findApply(Long jobPostId, Long userId);
}
