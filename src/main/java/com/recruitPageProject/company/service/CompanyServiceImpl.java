package com.recruitPageProject.company.service;

import com.recruitPageProject.company.dto.CompanyRequestDto;
import com.recruitPageProject.company.dto.CompanyResponseDto;
import com.recruitPageProject.company.entity.Company;
import com.recruitPageProject.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;

	@Override
	public CompanyResponseDto createCompany(CompanyRequestDto requestDto) {
		String name = requestDto.getName();
		String country = requestDto.getCountry();
		String city = requestDto.getCity();
		Company company = new Company(name, country, city);
		companyRepository.save(company);
		return new CompanyResponseDto(company);
	}
}
