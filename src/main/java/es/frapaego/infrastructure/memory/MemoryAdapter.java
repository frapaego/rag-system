package es.frapaego.infrastructure.memory;

import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryAdapter {

    private final Map<String, List<String>> memory = new ConcurrentHashMap<>();

    public List<String> get(String sessionId) {
        return memory.getOrDefault(sessionId, new ArrayList<>());
    }

    public void save(String sessionId, String msg) {
        memory.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(msg);
    }
}