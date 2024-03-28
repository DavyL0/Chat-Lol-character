package com.chatlol.adapters.out;

import com.chatlol.domain.ports.GenerativeAiApi;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GeminiApi.Config.class)
public interface GeminiApi extends GenerativeAiApi {
    @PostMapping("v1beta/models/gemini-pro:generateContent")
    GoogleGeminiResp textOnlyInput(GoogleGeminiReq req);

    @Override
    default String generateContent(String objective, String context) {
        String promp = """
                %s
                %s
                """.formatted(objective, context);

        GoogleGeminiReq req = new GoogleGeminiReq(
                List.of(new Contents(List.of(new Part(promp))))
        );
        try {
            GoogleGeminiResp resp = textOnlyInput(req);
            return resp.candidates().getFirst().contents().parts().getFirst().text();
        }catch (FeignException httpsError){
            return "Deu ruim! Erro de Comunicação com a API";
        }
        catch (Exception unexpectectedError) {
            return "Deu mais ruim ainda! O retorno com a API";
        }

    }

    record GoogleGeminiReq(List<Contents>contents) {}
    record Contents(List<Part>parts){}
    record Part(String text){}
    record GoogleGeminiResp(List<Candidate> candidates) {}
    record Candidate(Contents contents) {}
    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.query("key", apiKey);
        }
    }
}

