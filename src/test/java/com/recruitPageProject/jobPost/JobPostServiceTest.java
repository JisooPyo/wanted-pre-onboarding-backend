package com.recruitPageProject.jobPost;

import com.recruitPageProject.common.exception.CustomException;
import com.recruitPageProject.company.entity.Company;
import com.recruitPageProject.company.service.CompanyServiceImpl;
import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import com.recruitPageProject.jobPost.entity.JobPost;
import com.recruitPageProject.jobPost.repository.JobPostRepository;
import com.recruitPageProject.jobPost.service.JobPostServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static com.recruitPageProject.common.exception.CustomErrorCode.JOBPOST_NOT_FOUND;
import static com.recruitPageProject.common.exception.CustomErrorCode.NO_AUTHORIZATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobPostServiceTest {
	@Mock
	JobPostRepository jobPostRepository;
	@Mock
	CompanyServiceImpl companyService;
	@InjectMocks
	JobPostServiceImpl jobPostService;
	@Captor
	private ArgumentCaptor<JobPost> jobPostCaptor;

	@Test
	@DisplayName("채용 공고 등록 테스트")
	void createJobPostTest() {
		// given
		Long companyId = 100L;
		String position = "테스트 포지션";
		Long reward = 10000L;
		String skill = "테스트 스킬";
		String contents = "테스트 채용 공고";
		JobPostRequestDto requestDto = JobPostRequestDto.builder()
				.companyId(companyId)
				.position(position)
				.reward(reward)
				.skill(skill)
				.contents(contents)
				.build();

		Company mockCompany = new Company();
		when(companyService.findCompany(companyId)).thenReturn(mockCompany);

		// when
		JobPostFeedResponseDto responseDto = jobPostService.createJobPost(requestDto);

		// then
		verify(jobPostRepository, times(1)).save(jobPostCaptor.capture());

		JobPost capturedJobPost = jobPostCaptor.getValue();

		assertEquals(mockCompany, capturedJobPost.getCompany());
		assertEquals(reward, capturedJobPost.getReward());
		assertEquals(skill, capturedJobPost.getSkill());
		assertEquals(contents, capturedJobPost.getContents());
		assertEquals(position, capturedJobPost.getPosition());

	}

	@Test
	@DisplayName("채용 공고 수정 테스트 - 수정하려는 채용 공고가 없을 때")
	void updateJobPost1() {
		// given
		JobPostRequestDto requestDto = JobPostRequestDto.builder().build();

		when(jobPostRepository.findById(1L)).thenReturn(Optional.empty());

		// when
		CustomException exception = assertThrows(CustomException.class, () -> jobPostService.updateJobPost(requestDto, 1L));

		// then
		assertEquals(JOBPOST_NOT_FOUND, exception.getErrorCode());
		assertEquals("존재하지 않는 채용공고입니다.", exception.getErrorCode().getErrorMessage());
		assertEquals(HttpStatus.NOT_FOUND.value(), exception.getErrorCode().getErrorCode());
	}

	@Test
	@DisplayName("채용 공고 수정 테스트 - 권한이 있을 때")
	void updateJobPostTest2() {
		// given
		// 초기 JobPost 인스턴스 생성
		Company company = Company.builder().id(100L).build();
		JobPost jobPost = JobPost.builder()
				.id(37L).company(company).position("테스트 포지션")
				.reward(1_500_000L).skill("테스트 스킬")
				.contents("테스트 채용 공고 내용").build();

		// 수정 필요 정보 JobPostRequestDto
		JobPostRequestDto requestDto = JobPostRequestDto.builder()
				.companyId(100L)
				.position("테스트 포지션 2").reward(2_000_000L)
				.skill("테스트 스킬 2").contents("테스트 채용 공고 내용 2").build();

		when(jobPostRepository.findById(37L)).thenReturn(Optional.of(jobPost));

		// when
		jobPostService.updateJobPost(requestDto, 37L);

		// then
		assertEquals("테스트 포지션 2", jobPost.getPosition());
		assertEquals(2_000_000L, jobPost.getReward());
		assertEquals("테스트 스킬 2", jobPost.getSkill());
		assertEquals("테스트 채용 공고 내용 2", jobPost.getContents());
	}

	@Test
	@DisplayName("채용 공고 수정 테스트 - 권한이 없을 때")
	void updateJobPostTest3() {
		// given
		// 초기 JobPost 인스턴스 생성
		Company company = Company.builder().id(100L).build();
		JobPost jobPost = JobPost.builder()
				.id(37L).company(company).position("테스트 포지션")
				.reward(1_500_000L).skill("테스트 스킬")
				.contents("테스트 채용 공고 내용").build();

		// 수정 필요 정보 : 100번 회사의 채용공고를 50번 회사가 고치려고 시도
		JobPostRequestDto requestDto = JobPostRequestDto.builder()
				.companyId(50L)
				.position("테스트 포지션 2").reward(2_000_000L)
				.skill("테스트 스킬 2").contents("테스트 채용 공고 내용 2").build();

		when(jobPostRepository.findById(37L)).thenReturn(Optional.of(jobPost));

		// when
		CustomException exception = assertThrows(CustomException.class, () -> jobPostService.updateJobPost(requestDto, 37L));

		// then
		assertEquals(NO_AUTHORIZATION, exception.getErrorCode());
		assertEquals("권한이 없습니다.", exception.getErrorCode().getErrorMessage());
		assertEquals(HttpStatus.UNAUTHORIZED.value(), exception.getErrorCode().getErrorCode());

	}
}
