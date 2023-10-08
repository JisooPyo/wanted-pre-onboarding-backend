package com.recruitPageProject.apply;

import com.recruitPageProject.apply.dto.ApplyRequestDto;
import com.recruitPageProject.apply.entity.Apply;
import com.recruitPageProject.apply.repository.ApplyRepository;
import com.recruitPageProject.apply.service.ApplyServiceImpl;
import com.recruitPageProject.common.exception.CustomException;
import com.recruitPageProject.jobPost.entity.JobPost;
import com.recruitPageProject.jobPost.service.JobPostServiceImpl;
import com.recruitPageProject.user.entity.User;
import com.recruitPageProject.user.service.UserServiceImpl;
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

import static com.recruitPageProject.common.exception.CustomErrorCode.ALREADY_APPLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplyServiceTest {
	@Mock
	ApplyRepository applyRepository;
	@Mock
	UserServiceImpl userService;
	@Mock
	JobPostServiceImpl jobPostService;
	@InjectMocks
	ApplyServiceImpl applyService;
	@Captor
	private ArgumentCaptor<Apply> applyCaptor;

	@Test
	@DisplayName("채용 공고 지원 테스트 - 이전에 지원한 적이 없을 때")
	void createApply1() {
		// given
		User user = User.builder().id(3L).build();
		JobPost jobPost = JobPost.builder().id(11L).build();
		ApplyRequestDto requestDto = ApplyRequestDto.builder().userId(3L).jobPostId(11L).build();
		List<Apply> applyList = new ArrayList<>();
		when(applyRepository.findApply(11L, 3L)).thenReturn(applyList);
		when(userService.findUserById(3L)).thenReturn(user);
		when(jobPostService.findJobPost(11L)).thenReturn(jobPost);

		// when
		applyService.createApply(requestDto);

		// then
		verify(applyRepository, times(1)).save(applyCaptor.capture());

		Apply capturedApply = applyCaptor.getValue();

		assertEquals(11L, capturedApply.getJobPost().getId());
		assertEquals(3L, capturedApply.getUser().getId());
	}

	@Test
	@DisplayName("채용 공고 지원 테스트 - 이전에 지원한 적이 있을 때")
	void createApply2() {
		// given
		ApplyRequestDto requestDto = ApplyRequestDto.builder().userId(3L).jobPostId(11L).build();
		List<Apply> applyList = new ArrayList<>();
		Apply apply = new Apply();
		applyList.add(apply);
		when(applyRepository.findApply(11L, 3L)).thenReturn(applyList);

		// when
		CustomException exception = assertThrows(CustomException.class, () -> applyService.createApply(requestDto));

		// then
		assertEquals(ALREADY_APPLY, exception.getErrorCode());
		assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getErrorCode().getErrorCode());
		assertEquals("이미 지원하신 채용공고입니다.", exception.getErrorCode().getErrorMessage());
	}
}
