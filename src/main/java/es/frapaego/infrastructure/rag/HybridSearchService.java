package es.frapaego.infrastructure.rag;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HybridSearchService {

    public List<String> search(String query) {

        List<String> docs = List.of(
                "El SLA es 24 horas",
                "La API usa OAuth2",
                "La base de datos es PostgreSQL"
        );

        return docs.stream()
                .filter(d -> d.toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}