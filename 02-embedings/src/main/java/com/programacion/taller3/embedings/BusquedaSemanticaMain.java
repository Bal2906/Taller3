package com.programacion.taller3.embedings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;
import java.util.List;

public class BusquedaSemanticaMain {
    public static void main(String[] args) {
        var pathModel = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.json");
        EmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);

        var documentos = List.of(
                "java es el lenguaje de programacion orientada a objetos",
                "Python es el lenguaje popular en inteligencia artificial",
                "los embeddings representan palabras en un espacio vectorial"
        );

        List<Embedding> embeddings = documentos.stream()
                .map(model::embed)
                .map(Response::content)
                .toList();

        var consulta = "¿Qué lenguaje se usa en IA?";
        Embedding embeddingConsulta = model.embed(consulta).content();

        int index = 0;
        int mejorIndice = 0;
        double mmejorScore = -1.0;

        for (Embedding it : embeddings) {
            double score = CosineSimilarity.between(embeddingConsulta, it);
            if (score > mmejorScore) {
                mmejorScore = score;
                mejorIndice = index;
            }
            index++;
        }

        System.out.println("Documento más similar: " + documentos.get(mejorIndice));
        System.out.println("Score: " + mmejorScore);
    }
}