package com.recruitPageProject.company.service;

import com.recruitPageProject.company.dto.CompanyRequestDto;
import com.recruitPageProject.company.dto.CompanyResponseDto;

public interface CompanyService {
	/**
	 * @param requestDto
	 * @return CompanyResponseDto (회사 정보)
	 */
	CompanyResponseDto createCompany(CompanyRequestDto requestDto);
}
