package com.c4c.springai.config;

import org.springframework.ai.chroma.ChromaApi;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.ChromaVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class ChromaConfig {
  @Value("${chromadb.url:http://localhost:8000}")
  private String chromaDbUrl;

  @Value("${spring.ai.openai.embedding.api-key}")
  private String openApiKey;

  @Bean
  public RestClient.Builder builder() {
    return RestClient.builder().requestFactory(new SimpleClientHttpRequestFactory());
  }


  @Bean
  public ChromaApi chromaApi(RestClient.Builder restClientBuilder) {
    ChromaApi chromaApi = new ChromaApi(chromaDbUrl, restClientBuilder);
    return chromaApi;
  }

    @Bean
    public EmbeddingModel embeddingModel() {
      // Can be any other EmbeddingModel implementation.
      return new OpenAiEmbeddingModel(new OpenAiApi(openApiKey));
    }

  @Bean("vectorStore")
  public VectorStore chromaVectorStore(EmbeddingModel embeddingModel, ChromaApi chromaApi) {
    return new ChromaVectorStore(embeddingModel, chromaApi, "TestCollection", true);
  }
}
