package com.recruitPageProject.jobPost.entity;

import com.recruitPageProject.common.entity.Timestamped;
import com.recruitPageProject.company.entity.Company;
import com.recruitPageProject.jobPost.dto.JobPostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class JobPost extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(nullable = false)
	private String position;

	@Column(nullable = false)
	private String skill;

	@Column(nullable = false)
	private String contents;

	@Column(nullable = true)
	private Long reward;

	public JobPost(Company company, JobPostRequestDto requestDto) {
		this.company = company;
		this.position = requestDto.getPosition();
		this.skill = requestDto.getSkill();
		this.contents = requestDto.getContents();
		this.reward = requestDto.getReward();
	}

	public void update(JobPostRequestDto requestDto) {
		this.position = requestDto.getPosition();
		this.skill = requestDto.getSkill();
		this.contents = requestDto.getContents();
		this.reward = requestDto.getReward();
	}
}
