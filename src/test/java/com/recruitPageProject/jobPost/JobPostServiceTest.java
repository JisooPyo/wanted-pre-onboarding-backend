package com.recruitPageProject.jobPost;

import com.recruitPageProject.company.entity.Company;
import com.recruitPageProject.company.service.CompanyServiceImpl;
import com.recruitPageProject.jobPost.dto.JobPostFeedResponseDto;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import com.recruitPageProject.jobPost.entity.JobPost;
import com.recruitPageProject.jobPost.repository.JobPostRepository;
import com.recruitPageProject.jobPost.service.JobPostServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class JobPostServiceTest {
	@Mock
	JobPostRepository jobPostRepository;
	@Mock
	CompanyServiceImpl companyService;
	@Captor
	private ArgumentCaptor<JobPost> jobPostCaptor;

	@Test
	@DisplayName("채용 공고 등록 테스트")
	@Order(1)
	void createJobPostTest() {
		JobPostServiceImpl jobPostService = new JobPostServiceImpl(jobPostRepository, companyService);
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

		JobPostFeedResponseDto responseDto = jobPostService.createJobPost(requestDto);

		verify(jobPostRepository, times(1)).save(jobPostCaptor.capture());

		JobPost capturedJobPost = jobPostCaptor.getValue();

		assertEquals(mockCompany, capturedJobPost.getCompany());
		assertEquals(reward, capturedJobPost.getReward());
		assertEquals(skill, capturedJobPost.getSkill());
		assertEquals(contents, capturedJobPost.getContents());
		assertEquals(position, capturedJobPost.getPosition());

	}

	@Test
	@DisplayName("모든 채용 공고 조회 테스트")
	@Order(2)
	void getAllJobPostsTest() {

	}

	@Test
	@DisplayName("채용 공고 상세 조회 테스트")
	@Order(3)
	void getJobPostTest() {

	}

	@Test
	@DisplayName("채용 공고 검색 테스트")
	@Order(4)
	void searchJobPostsTest() {

	}

	@Test
	@DisplayName("채용 공고 수정 테스트")
	@Order(5)
	void updateJobPostTest() {

	}

	@Test
	@DisplayName("채용 공고 삭제 테스트")
	@Order(6)
	void deleteJobPostTest() {

	}
}

