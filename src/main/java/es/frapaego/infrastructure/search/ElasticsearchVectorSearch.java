package es.frapaego.infrastructure.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElasticsearchVectorSearch {

    private final ElasticsearchClient client;

    public ElasticsearchVectorSearch(ElasticsearchClient client) {
        this.client = client;
    }

    public List<String> search(List<Float> vector) {

        try {
            return client.search(s -> s
                            .index("rag-index")
                            .knn(k -> k
                                    .field("embedding")
                                    .queryVector(vector)
                                    .k(5)
                                    .numCandidates(100)
                            ),
                    MyDocument.class
            ).hits().hits().stream()
                    .map(h -> h.source().getContent())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}