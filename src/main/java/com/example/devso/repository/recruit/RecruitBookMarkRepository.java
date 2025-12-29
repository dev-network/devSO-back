package com.example.devso.repository.recruit;

import com.example.devso.entity.recruit.Recruit;
import com.example.devso.entity.recruit.RecruitBookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecruitBookMarkRepository extends JpaRepository<RecruitBookMark, Long> {
    //북마크 여부
    boolean existsByUserIdAndRecruitId(Long userId, Long RecruitId);
    //북마크 해제
    void deleteByUserIdAndRecruitId(Long userId, Long recruitId);
    //북마크 표시한 모집글
    @Query("SELECT rb.recruit.id FROM RecruitBookMark rb " +
            "WHERE rb.user.id = :userId AND rb.recruit.id IN :recruitIds")
    Set<Long> findRecruitIdsByUserIdAndRecruitIds(Long userId, List<Long> recruitIds);
}
