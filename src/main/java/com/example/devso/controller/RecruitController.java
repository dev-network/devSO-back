package com.example.devso.controller;

import com.example.devso.Security.CustomUserDetails;
import com.example.devso.dto.request.RecruitRequest;
import com.example.devso.dto.response.ApiResponse;
import com.example.devso.dto.response.RecruitResponse;
import com.example.devso.service.RecruitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruits")
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;

    //모집글 생성
    @PostMapping
    public ResponseEntity<ApiResponse<RecruitResponse>> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody RecruitRequest request
    ){
        RecruitResponse response = recruitService.create(userDetails.getId(),  request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    //전체 모집글 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<RecruitResponse>>> findAll(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        Long  userId = userDetails !=null ? userDetails.getId() : null;
        List<RecruitResponse> list = recruitService.findAll(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(list));
    }

    //모집글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecruitResponse>> findById(@PathVariable Long id){
        RecruitResponse response = recruitService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response));
    }

//
//    @PostMapping("/{id}/bookmark")
//    public void toggleBookmark(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
//        recruitService.toggleBookmark(userDetails, id);
//    }
//
//    @PostMapping("/{id}/comments")
//    public RecruitComment addComment(@PathVariable Long id, @RequestBody String content, @AuthenticationPrincipal CustomUserDetails userDetails) {
//        return recruitService.addComment(userDetails, id, content);
//    }
}
