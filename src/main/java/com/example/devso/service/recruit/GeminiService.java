package com.example.devso.service.recruit;

import com.example.devso.dto.request.recruit.GeminiRequest;
import com.example.devso.dto.response.recruit.GeminiResponse;
import com.example.devso.entity.recruit.Recruit;
import com.example.devso.repository.recruit.RecruitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final RecruitRepository recruitRepository;

    @Transactional
    public String getOrGenerateChecklist(Long recruitId) {
        // 1. 모집글 조회
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 2. 캐싱 로직: 이미 생성된 체크리스트가 있다면 바로 반환
        if (recruit.getAiChecklist() != null && !recruit.getAiChecklist().isBlank()) {
            return recruit.getAiChecklist();
        }

        // 3. AI에게 보낼 프롬프트 구성
        String prompt = String.format("""
            당신은 프로젝트 팀 빌딩 전문가입니다. 아래 모집글을 분석하여 지원자가 지원 전 스스로를 점검할 '자가진단 체크리스트'를 만드세요.
            
            [모집글 제목]: %s
            [모집글 내용]: %s
            
            [요구사항]
            1. 질문은 5개 이내로 생성.
            2. 기술 스택 일치 여부, 시간 약속, 협업 스타일 위주로 질문 구성.
            3. 반드시 순수 JSON 형식으로만 응답할 것. (코드 블록이나 설명 없이 JSON만)
            
            형식:
            {"checkList": [{"question": "질문내용", "target": "분류(기술/시간/태도 등)"}], "matchTip": "지원자를 위한 조언 한마디"}
            """, recruit.getTitle(), recruit.getContent());

        // 4. Gemini API 호출
        String aiResponse = callGeminiApi(prompt);

        // 5. 결과 저장 및 반환
        recruit.setAiChecklist(aiResponse);
        return aiResponse;
    }

    private String callGeminiApi(String prompt) {
        String url = apiUrl + "?key=" + apiKey;

        GeminiRequest request = new GeminiRequest(List.of(
                new GeminiRequest.Content(List.of(new GeminiRequest.Part(prompt)))
        ));

        try {
            GeminiResponse response = restTemplate.postForObject(url, request, GeminiResponse.class);
            if (response != null && !response.candidates().isEmpty()) {
                String result = response.candidates().get(0).content().parts().get(0).text();

                // 💡 AI가 응답에 포함할 수 있는 마크다운 태그 제거 (포트폴리오 안정성 포인트)
                return result.replace("```json", "").replace("```", "").trim();
            }
        } catch (Exception e) {
            // 예외 발생 시 기본 가이드 반환
            e.printStackTrace();
            return "{\"checkList\": [{\"question\": \"팀원과 원활한 소통이 가능하신가요?\", \"target\": \"기본\"}], \"matchTip\": \"AI 분석 서비스가 일시적으로 원활하지 않습니다.\"}";
        }
        return "";
    }
}
