package com.recruitPageProject.jobPost.entity;

import com.recruitPageProject.common.entity.Timestamped;
import com.recruitPageProject.company.entity.Company;
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

	@ManyToOne(fetch = FetchType.LAZY)
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

}
