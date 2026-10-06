package es.frapaego.infrastructure.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DateTool {

    @Tool("Devuelve la fecha actual")
    public String getDate() {
        return LocalDate.now().toString();
    }
}