package com.recruitPageProject.jobPost.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobPostRepositoryImpl implements JobPostCustomRepository{
	private final JPAQueryFactory queryFactory;
}
