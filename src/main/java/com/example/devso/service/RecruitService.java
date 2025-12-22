package com.example.devso.service;

import com.example.devso.dto.request.RecruitRequest;
import com.example.devso.dto.response.RecruitResponse;
import com.example.devso.entity.User;
import com.example.devso.entity.recruit.Recruit;
import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.example.devso.repository.RecruitBookMarkRepository;
import com.example.devso.repository.RecruitRepository;
import com.example.devso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final RecruitBookMarkRepository recruitBookMarkRepository;
    private final UserRepository userRepository;

    // 모집글 생성
    @Transactional
    public RecruitResponse create(Long userId, RecruitRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Recruit recruit = Recruit.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .type(request.getType())
                .position(request.getPosition())
                .progressType(request.getProgressType())
                .stacks(request.getStacks())
                .totalCount(request.getTotalCount())
                .currentCount(0)  // 기본 0
                .status(request.getStatus())
                .imageUrl(request.getImageUrl())
                .user(user)
                .build();

        Recruit saved = recruitRepository.save(recruit);
        return RecruitResponse.from(saved);
    }

    //모집글 전체 조회
    public List<RecruitResponse> findAll(Long currentUserId){
        List<Recruit> recruits = recruitRepository.findAllWithUser();
        return recruits.stream().map(recruit -> toRecruitResponseWithStatus(recruit, currentUserId)).toList();
    }

    //모집글 상세 조회
    @Transactional
    public RecruitResponse findById(Long recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUIT_NOT_FOUND));
        // 상세 조회에서만 조회수 증가
        recruit.increaseViewCount();
        return RecruitResponse.from(recruit);
    }


    //Recruit엔티티와 사용자 정보로 상태 반환
    private RecruitResponse toRecruitResponseWithStatus(Recruit recruit, Long currentUserId) {
        //북마크 여부
        boolean bookmarked = currentUserId != null
                && recruitBookMarkRepository.existsByUserIdAndRecruitId(currentUserId, recruit.getId());
        return RecruitResponse.from(recruit, bookmarked);
    }
}
