package com.recruitPageProject.apply.repository;

import com.recruitPageProject.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long>, ApplyCustomRepository {
}
