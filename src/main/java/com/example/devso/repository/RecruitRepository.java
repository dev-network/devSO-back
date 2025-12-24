package com.example.devso.repository;

import com.example.devso.entity.recruit.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitRepository  extends JpaRepository<Recruit,Long> {
    //전체 모집글 조회
    @Query("SELECT r FROM Recruit r JOIN FETCH r.user ORDER BY r.createdAt DESC")
    List<Recruit> findAllWithUser();

    //모집글 상세 조회
    @Query("SELECT r FROM Recruit r JOIN FETCH r.user WHERE r.id = :id")
    Optional<Recruit> findByIdWithDetails(@Param("id") Long id);
}
