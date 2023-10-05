package com.recruitPageProject.company.service;

import com.recruitPageProject.company.dto.CompanyRequestDto;
import com.recruitPageProject.company.dto.CompanyResponseDto;

public interface CompanyService {
	/**
	 * @param requestDto(회사 등록 요청 정보)
	 * @return CompanyResponseDto (회사 정보)
	 */
	CompanyResponseDto createCompany(CompanyRequestDto requestDto);
}
