package es.frapaego.infrastructure.graph;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GraphStore {

    private final Map<String, GraphNode> nodes = new HashMap<>();
    private final List<GraphEdge> edges = new ArrayList<>();

    public void addNode(GraphNode node) {
        nodes.put(node.getId(), node);
    }

    public void addEdge(GraphEdge edge) {
        edges.add(edge);
    }

    public List<GraphNode> find(String keyword) {

        return nodes.values().stream()
                .filter(n -> n.getValue().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}