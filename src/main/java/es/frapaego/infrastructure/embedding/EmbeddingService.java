package es.frapaego.infrastructure.embedding;

import dev.langchain4j.model.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbeddingService {

    private final EmbeddingModel model;

    public EmbeddingService(EmbeddingModel model) {
        this.model = model;
    }

    public List<Float> embed(String text) {
        return model.embed(text).content().vectorAsList();
    }
}