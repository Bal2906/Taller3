package com.programacion.taller3.embedings;

import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;

public class SimilitudMain {
    static void main() {
        var pathModel = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Música/Taller3/Models/multilingual-e5-small.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);

        var embeding1 = model.embed("Cual es el area de un circulo?").content();
        var embeding2 = model.embed("¿Cual es el area de un cuadrado?").content();
        var embedding3 = model.embed("Que hora es?").content();

        //Calcular la similitud coseno entre los embeddings
        double similitud12 = CosineSimilarity.between(embeding1, embeding2);
        double similitud13 = CosineSimilarity.between(embeding1, embedding3);
        double similitud23 =  CosineSimilarity.between(embeding2, embedding3);

        System.out.println("Similitud entre embeding1 y embeding2: " + similitud12);
        System.out.println("Similitud entre embeding1 y embedding3: " + similitud13);
        System.out.println("Similitud entre embeding2 y embedding3: " + similitud23);
    }
}
