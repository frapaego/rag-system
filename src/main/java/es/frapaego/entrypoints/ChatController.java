package es.frapaego.entrypoints;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import es.frapaego.application.ChatService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService service;
    private final StreamingChatLanguageModel streamingModel;

    public ChatController(ChatService service, StreamingChatLanguageModel streamingModel) {
        this.service = service;
        this.streamingModel = streamingModel;
    }

    @PostMapping
    public String chat(@RequestBody String question) {
        return service.chat(question);
    }
    
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam String question) {

        return Flux.create(emitter -> {

            String prompt = service.chat(question); // reutiliza contexto

            streamingModel.generate(prompt, new StreamingResponseHandler<>() {

                @Override
                public void onNext(String token) {
                    emitter.next(token);
                }

                @Override
                public void onComplete(Response response) {
                    emitter.complete();
                }

                @Override
                public void onError(Throwable error) {
                    emitter.error(error);
                }
            });
        });
    }
}