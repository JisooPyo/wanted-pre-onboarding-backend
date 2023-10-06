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

	@Override
	public List<JobPost> searchJobPosts(String search) {
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(search);
		sb.append("%");
		String likeSearch = sb.toString();
		return queryFactory.selectFrom(jobPost)
				.where(
						jobPost.company.name.like(likeSearch)
								.or(jobPost.company.city.like(likeSearch))
								.or(jobPost.company.country.like(likeSearch))
								.or(jobPost.position.like(likeSearch))
								.or(jobPost.contents.like(likeSearch))
								.or(jobPost.position.like(likeSearch))
								.or(jobPost.skill.like(likeSearch))
				)
				.orderBy(jobPost.id.desc())
				.fetch();
	}
}
