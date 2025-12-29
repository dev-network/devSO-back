package com.example.devso.service;

import com.example.devso.dto.response.LikeResponse;
import com.example.devso.entity.Post;
import com.example.devso.entity.PostLike;
import com.example.devso.entity.User;
import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.example.devso.repository.PostLikeRepository;
import com.example.devso.repository.PostRepository;
import com.example.devso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeResponse like(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        Post post = postRepository.findByIdAndDeletedAtIsNull(postId)
                .orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));

        // 이미 좋아요 했는지 확인
        if(postLikeRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        }

        PostLike postLike = PostLike.builder()
                .user(user)
                .post(post)
                .build();

        postLikeRepository.save(postLike);

        long likeCount = postLikeRepository.countByPostId(postId);
        return LikeResponse.of(true, likeCount);

    }

    @Transactional
    public LikeResponse unlike(Long userId, Long postId) {
        if (!postRepository.existsByIdAndDeletedAtIsNull(postId)) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        PostLike postLike = postLikeRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_LIKED));

        postLikeRepository.delete(postLike);

        long likeCount = postLikeRepository.countByPostId(postId);
        return LikeResponse.of(false, likeCount);
    }

    public LikeResponse getLikeStatus(Long postId, Long userId) {
        if(!postRepository.existsByIdAndDeletedAtIsNull(postId)) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        boolean liked = userId != null && postLikeRepository.existsByUserIdAndPostId(userId, postId);
        long likeCount = postLikeRepository.countByPostId(postId);

        return LikeResponse.of(liked, likeCount);

    }
}
