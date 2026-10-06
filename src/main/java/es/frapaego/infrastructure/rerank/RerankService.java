package es.frapaego.infrastructure.rerank;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RerankService {

    public List<String> rerank(String query, List<String> docs) {

        return docs.stream()
                .sorted((a, b) -> score(b, query) - score(a, query))
                .limit(5)
                .toList();
    }

    private int score(String doc, String q) {
        return doc.contains(q) ? 10 : 1;
    }
}