package com.example.devso.dto.response.recruit;

import java.util.List;

public record AiChecklistResponse(
        List<CheckItem> checkList,
        String matchTip
) {
    public record CheckItem(String question, String target) {}
}
