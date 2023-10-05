package com.recruitPageProject.company.controller;

import com.recruitPageProject.company.dto.CompanyRequestDto;
import com.recruitPageProject.company.dto.CompanyResponseDto;
import com.recruitPageProject.company.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Company API", description = "Company CRUD를 위한 API 정보를 담고 있습니다")
public class CompanyController {
	private final CompanyService companyService;

	@Operation(summary = "회사 등록", description = "등록에 필요한 정보를 받아 회사를 등록합니다.")
	@PostMapping("/companys")
	public ResponseEntity<CompanyResponseDto> createCompany(@Valid @RequestBody CompanyRequestDto requestDto) {
		CompanyResponseDto responseDto = companyService.createCompany(requestDto);
		return ResponseEntity.ok().body(responseDto);
	}
}
