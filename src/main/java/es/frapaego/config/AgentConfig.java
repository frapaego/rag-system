package es.frapaego.config;

import dev.langchain4j.service.AiServices;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import es.frapaego.infrastructure.tools.DateTool;

@Configuration
public class AgentConfig {

    @Bean
    public Assistant assistant(ChatLanguageModel model, DateTool tool) {
        return AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(tool)
                .build();
    }

    public interface Assistant {
        String chat(String message);    }
}