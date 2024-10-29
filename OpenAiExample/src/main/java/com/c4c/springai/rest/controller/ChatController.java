package com.c4c.springai.rest.controller;

import com.c4c.springai.rest.resource.DocumentResource;
import com.c4c.springai.service.OpenAiChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chat/")
@RestController
public class ChatController {
  private final OpenAiChatService openAiChatService;

  public ChatController(final OpenAiChatService openAiChatService) {
    this.openAiChatService = openAiChatService;
  }

  @PostMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
  public ResponseEntity<String > add(@RequestBody String query){

    ChatResponse chat = this.openAiChatService.chat(query);
    return ResponseEntity.ok(chat.toString());
  }
}
