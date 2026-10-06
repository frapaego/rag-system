package es.frapaego.infrastructure.graph;

import java.util.UUID;

public class GraphNode {
    private String id = UUID.randomUUID().toString();
    private String value;

    public GraphNode(String value) {
        this.value = value;
    }

    public String getId() { return id; }
    public String getValue() { return value; }
}