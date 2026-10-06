package es.frapaego.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LlmConfig {
	
	@Value("${openai.api-key}")
	private String openAiApiKey;
	
	@Bean
	public EmbeddingModel embeddingModel() {
	    return OpenAiEmbeddingModel.builder()
	            .apiKey(openAiApiKey)
	            .modelName("text-embedding-3-large")
	            .build();
	}

    @Bean
    public ChatLanguageModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(openAiApiKey)
                .modelName("gpt-4o-mini")
                .temperature(0.2)
                .build();
    }

    @Bean
    public StreamingChatLanguageModel streamingModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(openAiApiKey)
                .modelName("gpt-4o-mini")
                .temperature(0.2)
                .build();
    }
}