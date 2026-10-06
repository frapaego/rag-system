package es.frapaego.infrastructure.graph;

import org.neo4j.driver.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Neo4jAdapter {

    private final Driver driver;

    public Neo4jAdapter() {
        this.driver = GraphDatabase.driver(
                "bolt://neo4j:7687",
                AuthTokens.basic("neo4j", "password")
        );
    }

    public List<String> findRelated(String keyword) {

        try (Session session = driver.session()) {

            return session.run("""
                MATCH (n)-[:RELATED]->(m)
                WHERE n.name CONTAINS $keyword
                RETURN m.name LIMIT 5
            """, Values.parameters("keyword", keyword))
                    .list(r -> r.get("m.name").asString());
        }
    }
}