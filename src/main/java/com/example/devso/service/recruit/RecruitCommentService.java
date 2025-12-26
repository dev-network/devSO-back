package com.example.devso.service.recruit;

import com.example.devso.dto.request.recruit.RecruitCommentRequest;
import com.example.devso.dto.response.recruit.RecruitCommentResponse;
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

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitCommentService {
    private final RecruitCommentRepository recruitCommentRepository;
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    @Transactional
    public RecruitCommentResponse create(Long recruitId, Long userId, RecruitCommentRequest request) {
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new CustomException(ErrorCode.RECRUIT_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        RecruitComment comment = RecruitComment.create(request.getContent(), user, recruit);
        recruitCommentRepository.save(comment);

        recruit.increaseCommentCount();

        return RecruitCommentResponse.from(comment, userId);
    }

    //특정 모집글의 댓글 조회
    public List<RecruitCommentResponse> findByRecruitId(Long recruitId, Long currentUserId) {
        if (!recruitRepository.existsById(recruitId)) {
            throw new CustomException(ErrorCode.RECRUIT_NOT_FOUND);
        }

        List<RecruitComment> comments = recruitCommentRepository.findByRecruitIdWithUser(recruitId);
        return comments.stream()
                .map(comment -> RecruitCommentResponse.from(comment, currentUserId))
                .toList();
    }

    // 댓글 수정
    @Transactional
    public RecruitCommentResponse update(Long commentId, Long userId, RecruitCommentRequest request) {
        RecruitComment comment = recruitCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        // 작성자 본인 확인
        if (!comment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.NOT_COMMENT_OWNER);
        }

        comment.update(request.getContent());
        return RecruitCommentResponse.from(comment, userId);
    }

    // 댓글 삭제
    @Transactional
    public void delete(Long commentId, Long userId) {
        RecruitComment comment = recruitCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.NOT_COMMENT_OWNER);
        }

        comment.getRecruit().decreaseCommentCount();

        recruitCommentRepository.delete(comment);
    }
}