package es.frapaego.infrastructure.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ElasticsearchIndexer {

    private final ElasticsearchClient client;

    public ElasticsearchIndexer(ElasticsearchClient client) {
        this.client = client;
    }

    public void index(MyDocument doc) {

        try {
            client.index(i -> i
                    .index("rag-index")
                    .id(UUID.randomUUID().toString())
                    .document(doc)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}