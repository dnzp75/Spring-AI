package com.example.wear.demo.AI;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ApiKeyChecker {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @PostConstruct
    public void checkKey() {
        System.out.println("Loaded API Key: " + apiKey);
        System.out.println("System Environment Variable: " + System.getenv("SPRING_AI_OPENAI_API_KEY"));

    }
}
