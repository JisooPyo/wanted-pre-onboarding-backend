package com.recruitPageProject.company.dto;

import com.recruitPageProject.company.entity.Company;
import lombok.Getter;

@Getter
public class CompanyResponseDto {
	private Long id;
	private String name;
	private String country;
	private String city;

	public CompanyResponseDto(Company company) {
		this.id = company.getId();
		this.name = company.getName();
		this.country = company.getCountry();
		this.city = company.getCity();
	}
}
