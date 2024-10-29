package com.c4c.springai.service;

import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class VectorStoreService {
  private final VectorStore vectorStore;

  public VectorStoreService(final VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  public void add(Document document){
    this.add(List.of(document));
  }

  public void add(List<Document> documents){
    this.vectorStore.add(documents);
  }

  public List<Document> similaritySearch(String query, int topK){
    SearchRequest searchRequest = SearchRequest.query(query);
    searchRequest.withTopK(topK);
    return this.vectorStore.similaritySearch(searchRequest);
  }

  public List<Document> similaritySearch(String query){
    return this.similaritySearch(query, 5);
  }
}
