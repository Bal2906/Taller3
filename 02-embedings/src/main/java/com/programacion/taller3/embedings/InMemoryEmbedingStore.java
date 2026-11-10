package com.programacion.taller3.embedings;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.Paths;
import java.util.List;

public class InMemoryEmbedingStore {
    public static void main(String[] args) {
        var pathModel = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.json");

        EmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);

        var documentos = List.of(
                "java es el lenguaje de programacion orientada a objetos",
                "Python es el lenguaje popular en inteligencia artificial",
                "los embeddings representan palabras en un espacio vectorial"
        );

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        int idCounter = 1;
        for (String it : documentos) {
            String key = "doc" + idCounter++;
            var embedding = model.embed(it);

            embeddingStore.add(key, embedding.content(), TextSegment.from(it));
        }

        var consulta = "programacion orientada a objetos";
        var searchQuery = EmbeddingSearchRequest.builder()
                .queryEmbedding(model.embed(consulta).content())
                .maxResults(2)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchQuery);

        for (var it : searchResult.matches()) {
            System.out.printf("Score: %.4f - %s\n", it.score(), it.embedded().text());
        }
    }
}