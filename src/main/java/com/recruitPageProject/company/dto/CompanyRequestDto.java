package com.recruitPageProject.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CompanyRequestDto {
	@NotBlank
	String name;
	@NotBlank
	String country;
	@NotBlank
	String city;
}
