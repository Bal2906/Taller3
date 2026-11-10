package com.programacion.taller3.embedings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

import java.nio.file.Paths;

public class EmbedingsModelMain {
    public static void main(String[] args) {
        var pathModel = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.json");

        EmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);

        var texto = "Hola como estas?";
        Response<Embedding> response = model.embed(texto);
        Embedding embedding = response.content();

        System.out.println("Dimension of the embedding: " + embedding.dimension());
        System.out.println("Embedding Vector: " + embedding.vectorAsList());
    }
}