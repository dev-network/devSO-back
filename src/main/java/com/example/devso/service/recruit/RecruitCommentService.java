package com.example.devso.service.recruit;

import com.example.devso.dto.request.recruit.RecruitCommentRequest;
import com.example.devso.dto.response.recruit.RecruitCommentResponse;
import com.example.devso.dto.response.recruit.RecruitResponse;
import com.example.devso.entity.User;
import com.example.devso.entity.recruit.Recruit;
import com.example.devso.entity.recruit.RecruitComment;
import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.example.devso.repository.UserRepository;
import com.example.devso.repository.recruit.RecruitCommentRepository;
import com.example.devso.repository.recruit.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class RecruitCommentService {
    private final RecruitCommentRepository recruitCommentRepository;
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;

    //댓글 생성
    @Transactional
    public RecruitCommentResponse create(Long recruitId, Long userId, RecruitCommentRequest request){
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUIT_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        RecruitComment comment = RecruitComment.create(request.getContent(), user, recruit);
        recruitCommentRepository.save(comment);
        return RecruitCommentResponse.from(comment);
    }
}
