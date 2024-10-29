package com.c4c.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
  @Value("${spring.ai.openai.embedding.api-key}")
  private String openApiKey;
  @Bean
  public ChatModel chatClient(){
    var openAiApi = new OpenAiApi(openApiKey);
    var openAiChatOptions = OpenAiChatOptions.builder()
        .withModel("gpt-3.5-turbo")
        .withTemperature(0.4)
        .withMaxTokens(1200)
        .build();
    return new OpenAiChatModel(openAiApi, openAiChatOptions);
  }
}
