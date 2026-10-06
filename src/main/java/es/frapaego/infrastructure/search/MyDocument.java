package es.frapaego.infrastructure.search;

import java.util.List;

public class MyDocument {

    private String content;
    private List<Float> embedding;

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public List<Float> getEmbedding() { return embedding; }
    public void setEmbedding(List<Float> embedding) { this.embedding = embedding; }
}