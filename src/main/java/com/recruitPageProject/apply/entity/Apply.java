package com.recruitPageProject.apply.entity;

import com.recruitPageProject.common.entity.Timestamped;
import com.recruitPageProject.jobPost.entity.JobPost;
import com.recruitPageProject.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apply extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_post_id")
	private JobPost jobPost;

	public Apply(User user, JobPost jobPost) {
		this.user = user;
		this.jobPost = jobPost;
	}
}
