package com.recruitPageProject.jobPost.dto;

import com.recruitPageProject.jobPost.entity.JobPost;
import lombok.Getter;

@Getter
public class JobPostFeedResponseDto {
	private Long id;
	private String company;
	private String country;
	private String city;
	private String position;
	private Long reward;
	private String skill;

	public JobPostFeedResponseDto(JobPost jobPost) {
		this.id = jobPost.getId();
		this.company = jobPost.getCompany().getName();
		this.country = jobPost.getCompany().getCountry();
		this.city = jobPost.getCompany().getCity();
		this.position = jobPost.getPosition();
		this.reward = jobPost.getReward();
		this.skill = jobPost.getSkill();
	}
}
