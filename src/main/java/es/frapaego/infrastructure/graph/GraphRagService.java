package es.frapaego.infrastructure.graph;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GraphRagService {

    private final GraphStore store;

    public GraphRagService(GraphStore store) {
        this.store = store;
    }

    public List<String> retrieve(String query) {

        return store.find(query)
                .stream()
                .map(GraphNode::getValue)
                .toList();
    }
}