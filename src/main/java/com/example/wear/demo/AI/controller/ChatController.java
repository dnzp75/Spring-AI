package com.example.wear.demo.AI.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ChatController {

    private final OpenAiChatModel openAiChatModel;

    public ChatController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> requestBody) {
        Map<String, String> responses = new HashMap<>();

        // 사용자 답변 가져오기
        String userResponse = requestBody.get("message");

        if (userResponse == null || userResponse.trim().isEmpty()) {
            responses.put("error", "답변 내용이 비어 있습니다. 유효한 답변을 입력해 주세요.");
            return responses;
        }

        // 고정된 컨텍스트 추가
        String prompt =
          "이 요청은 신입사원이 직장에서 문해력과 의사소통 능력을 향상시키기 위해 작성한 답변에 대한 피드백을 요청하는 것입니다. "
            + "답변에 대해서 개선하면 좋은 점에 대한 문장 예시를 보여주면서 어떠한 근거로 이러한 부분이 개선이 되는지를 설명해주세요\n\n" +

          "작성된 답변은 다음과 같습니다:\n" +
          "\"" + userResponse + "\"\n\n";

        // OpenAI API 호출
        String openAiResponse = openAiChatModel.call(prompt);

        String formattedResponse = openAiResponse.replace("\\n", "<br>");
        responses.put("openai(chatGPT) 응답", formattedResponse);

        return responses;
    }

}
//asdfasdf

