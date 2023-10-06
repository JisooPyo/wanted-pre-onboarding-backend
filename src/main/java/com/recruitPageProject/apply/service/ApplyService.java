package com.recruitPageProject.apply.service;

import com.recruitPageProject.apply.dto.ApplyRequestDto;

public interface ApplyService {
	/**
	 * @param requestDto (채용 공고 지원에 필요한 정보)
	 */
	void createApply(ApplyRequestDto requestDto);
}
