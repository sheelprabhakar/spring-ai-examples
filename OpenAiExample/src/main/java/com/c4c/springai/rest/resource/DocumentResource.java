package com.c4c.springai.rest.resource;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResource {
  private String content;
  private Map<String, Object>metaData;
}
