package com.recruitPageProject.user.service;

import com.recruitPageProject.common.exception.CustomErrorCode;
import com.recruitPageProject.common.exception.CustomException;
import com.recruitPageProject.user.dto.SignupRequestDto;
import com.recruitPageProject.user.entity.User;
import com.recruitPageProject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void signup(SignupRequestDto requestDto) {
		User user = findUserByEmail(requestDto.getEmail());
		if (user != null) {
			throw new CustomException(CustomErrorCode.ALREADY_SIGNUP);
		}
		String email = requestDto.getEmail();
		String password = passwordEncoder.encode(requestDto.getPassword());
		User targetUser = User.builder().email(email).password(password).build();
		userRepository.save(targetUser);
	}

	public User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() ->
				new CustomException(CustomErrorCode.USER_NOT_FOUND));
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
