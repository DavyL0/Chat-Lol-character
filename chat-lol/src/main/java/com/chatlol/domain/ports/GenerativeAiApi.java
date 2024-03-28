package com.chatlol.domain.ports;

public interface GenerativeAiApi {
    String generateContent(String objective, String context);
}
