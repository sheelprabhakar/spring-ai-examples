package com.c4c.springai.rest.controller;

import com.c4c.springai.rest.resource.DocumentResource;
import com.c4c.springai.service.VectorStoreService;
import java.util.List;
import org.apache.coyote.Response;
import org.springframework.ai.document.Document;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document/")
public class DocumentController {
  private final VectorStoreService vectorStoreService;

  public DocumentController(final VectorStoreService vectorStoreService) {
    this.vectorStoreService = vectorStoreService;
  }

  @PostMapping(consumes = MediaType.ALL_VALUE)
  public ResponseEntity<Void> add(@RequestBody DocumentResource resource){
    Document document = new Document(resource.getContent(), resource.getMetaData());
    this.vectorStoreService.add(document);
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
  }

  @GetMapping()
  public ResponseEntity<List<Document>> search(@RequestParam(value = "q", required = true) String query ){
    List<Document> documents = this.vectorStoreService.similaritySearch(query);
    return ResponseEntity.ok(documents);
  }
}
