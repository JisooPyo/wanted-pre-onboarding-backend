package com.recruitPageProject.jobPost;

import com.recruitPageProject.common.exception.CustomException;
import com.recruitPageProject.company.entity.Company;
import com.recruitPageProject.company.service.CompanyServiceImpl;
import com.recruitPageProject.jobPost.dto.JobPostDeleteRequestDto;
import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import com.recruitPageProject.jobPost.dto.JobPostResponseDto;
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

import java.util.ArrayList;
import java.util.List;
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
		JobPost jobPost = initJobPost(100L, 37L);

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
		JobPost jobPost = initJobPost(100L, 37L);

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

	@Test
	@DisplayName("채용 공고 삭제 테스트")
	void deleteJobPostTest() {
		// given
		JobPost jobPost = initJobPost(100L, 37L);
		JobPostDeleteRequestDto requestDto = JobPostDeleteRequestDto.builder().companyId(100L).build();

		when(jobPostRepository.findById(37L)).thenReturn(Optional.of(jobPost));

		// when
		jobPostService.deleteJobPost(requestDto, 37L);

		// then
		verify(jobPostRepository, times(1)).deleteById(37L);
	}

	@Test
	@DisplayName("모든 채용 공고 목록 조회 테스트")
	void getAllJobPostsTest() {
		// given
		// 가상의 JobPost 객체들을 생성
		List<JobPost> mockJobPostList = new ArrayList<>();
		mockJobPostList.add(initJobPost(2L, 6L));
		mockJobPostList.add(initJobPost(2L, 5L));
		mockJobPostList.add(initJobPost(2L, 4L));
		mockJobPostList.add(initJobPost(1L, 3L));
		mockJobPostList.add(initJobPost(1L, 2L));
		mockJobPostList.add(initJobPost(1L, 1L));

		when(jobPostRepository.findAllByOrderByIdDesc()).thenReturn(mockJobPostList);

		// when
		List<JobPostFeedResponseDto> result = jobPostService.getAllJobPosts();

		// then
		assertEquals(mockJobPostList.size(), result.size());
	}

	@Test
	@DisplayName("채용 공고 상세 조회 테스트")
	void getJobPostTest() {
		// given
		JobPost jobPost1 = initJobPost(1L, 1L);
		JobPost jobPost2 = initJobPost(1L, 2L);
		JobPost jobPost3 = initJobPost(1L, 3L);
		JobPost jobPost4 = initJobPost(1L, 4L);

		List<JobPost> jobPostList = new ArrayList<>();
		jobPostList.add(jobPost1);
		jobPostList.add(jobPost2);
		jobPostList.add(jobPost4);

		when(jobPostRepository.findById(3L)).thenReturn(Optional.of(jobPost3));
		when(jobPostRepository.findOtherJobPosts(3L)).thenReturn(jobPostList);

		// when
		JobPostResponseDto responseDto = jobPostService.getJobPost(3L);

		// then
		assertEquals(jobPostList.size(), responseDto.getOtherJobPosts().size());
		assertEquals(3L, responseDto.getId());
		assertEquals("테스트 포지션", responseDto.getPosition());
		assertEquals("테스트 스킬", responseDto.getSkill());
		assertEquals(1_500_000L, responseDto.getReward());
		assertEquals("테스트 채용 공고 내용", responseDto.getContents());
	}

	JobPost initJobPost(Long companyId, Long jobPostId) {
		// 초기 JobPost 인스턴스 생성
		Company company = Company.builder().id(companyId).build();
		JobPost jobPost = JobPost.builder()
				.id(jobPostId).company(company).position("테스트 포지션")
				.reward(1_500_000L).skill("테스트 스킬")
				.contents("테스트 채용 공고 내용").build();
		return jobPost;
	}
}
