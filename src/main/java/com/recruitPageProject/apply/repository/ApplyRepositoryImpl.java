package com.recruitPageProject.apply.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recruitPageProject.apply.entity.Apply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.recruitPageProject.apply.entity.QApply.apply;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyCustomRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Apply> findApply(Long jobPostId, Long userId) {
		return queryFactory.selectFrom(apply)
				.where(apply.jobPost.id.eq(jobPostId).and(apply.user.id.eq(userId)))
				.fetch();
	}
}
