package com.recruitPageProject.jobPost.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recruitPageProject.jobPost.entity.JobPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.recruitPageProject.jobPost.entity.QJobPost.jobPost;

@Repository
@RequiredArgsConstructor
public class JobPostRepositoryImpl implements JobPostCustomRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<JobPost> findOtherJobPosts(Long id) {
		return queryFactory.selectFrom(jobPost)
				.where(jobPost.company.id.in(
						JPAExpressions.select(jobPost.company.id)
								.from(jobPost)
								.where(jobPost.id.eq(id))
				).and(jobPost.id.ne(id)))
				.fetch();
	}
}
