package com.example.meosasshu.common.openai;

import com.theokanning.openai.service.OpenAiService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ChatGPTConfig {
    @Value("${gpt.token}")
    private String token;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(token, Duration.ofSeconds(60));
    }
}
