package com.c4c.springai.service;

import java.util.List;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

@Service
public class OpenAiChatService {
  private final ChatModel chatClient;
  private final VectorStoreService vectorStoreService;

  private final String PROMPT_BLUEPRINT = """
      {context}
      Query:
      {query}
      In case you don't have any answer from the context provided, just say:
      I'm sorry I don't have the information you are looking for.
    """;

  public OpenAiChatService(final ChatModel chatClient, final VectorStoreService vectorStoreService) {
    this.chatClient = chatClient;
    this.vectorStoreService = vectorStoreService;
  }

  public ChatResponse chat(String query) {
    return chatClient.call(createPrompt(query, vectorStoreService.similaritySearch(query)));
  }

  private Prompt createPrompt(String query, List<Document> context) {
    PromptTemplate promptTemplate = new PromptTemplate(PROMPT_BLUEPRINT);
    promptTemplate.add("query", query);
    promptTemplate.add("context", context.get(0));
    return promptTemplate.create();
  }
}
