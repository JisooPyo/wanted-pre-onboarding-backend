package com.recruitPageProject.jobPost.dto;

import com.recruitPageProject.jobPost.entity.JobPost;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JobPostResponseDto {
	private Long id;
	private String company;
	private String country;
	private String city;
	private String position;
	private Long reward;
	private String skill;
	private String contents;
	private List<Long> otherJobPosts = new ArrayList<>();

	public JobPostResponseDto(JobPost jobPost) {
		this.id = jobPost.getId();
		this.company = jobPost.getCompany().getName();
		this.country = jobPost.getCompany().getCountry();
		this.city = jobPost.getCompany().getCity();
		this.position = jobPost.getPosition();
		this.reward = jobPost.getReward();
		this.skill = jobPost.getSkill();
		this.contents = jobPost.getContents();
	}

	public void addOtherJobPosts(List<Long> otherJobPosts) {
		this.otherJobPosts = otherJobPosts;
	}
}
