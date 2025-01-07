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
    public Map<String, String> chat(@RequestBody String message) {
        Map<String, String> responses = new HashMap<>();

        // OpenAI API 호출
        String openAiResponse = openAiChatModel.call(message);

        // \n 문자열을 실제 줄바꿈으로 변환
        openAiResponse = openAiResponse.replace("\n", "");

        // 응답 추가
        responses.put("openai(chatGPT) 응답", openAiResponse);

        return responses;
    }

}


