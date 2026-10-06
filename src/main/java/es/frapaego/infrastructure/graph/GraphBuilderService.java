package es.frapaego.infrastructure.graph;

import org.springframework.stereotype.Service;

@Service
public class GraphBuilderService {

    private final GraphStore store;

    public GraphBuilderService(GraphStore store) {
        this.store = store;
    }

    public void build(String text) {

        String[] words = text.split(" ");

        for (String w : words) {
            store.addNode(new GraphNode(w));
        }
    }
}