package com.example.devso.repository;

import com.example.devso.entity.recruit.RecruitBookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitBookMarkRepository extends JpaRepository<RecruitBookMark, Long> {
    //북마크 여부
    boolean existsByUserIdAndRecruitId(Long userId, Long RecruitId);
}
