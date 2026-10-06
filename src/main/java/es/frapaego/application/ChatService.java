package es.frapaego.application;

import org.springframework.stereotype.Service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import es.frapaego.infrastructure.embedding.EmbeddingService;
import es.frapaego.infrastructure.search.ElasticsearchVectorSearch;
import es.frapaego.infrastructure.graph.Neo4jAdapter;
import es.frapaego.infrastructure.cache.SemanticCacheService;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ChatService {

    private final EmbeddingService embedding;
    private final ElasticsearchVectorSearch search;
    private final Neo4jAdapter graph;
    private final SemanticCacheService cache;
    private final ChatLanguageModel llm;

    public ChatService(EmbeddingService embedding,
                       ElasticsearchVectorSearch search,
                       Neo4jAdapter graph,
                       SemanticCacheService cache,
                       ChatLanguageModel llm) {
        this.embedding = embedding;
        this.search = search;
        this.graph = graph;
        this.cache = cache;
        this.llm = llm;
    }

    public String chat(String question) {

        String key = cache.key(question);
        String cached = cache.get(key);
        if (cached != null) return cached;

        List<Float> vector = embedding.embed(question);

        List<String> docs = search.search(vector);
        List<String> graphDocs = graph.findRelated(question);

        List<String> context = Stream.concat(docs.stream(), graphDocs.stream())
                .limit(10)
                .toList();

        String prompt = """
        Eres un asistente experto.

        Usa el contexto para responder.

        CONTEXTO:
        %s

        PREGUNTA:
        %s
        """.formatted(
                String.join("\n---\n", context),
                question
        );

        String response = llm.generate(prompt);

        cache.put(key, response);

        return response;
    }
}