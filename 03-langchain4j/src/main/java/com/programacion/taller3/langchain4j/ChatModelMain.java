package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class ChatModelMain {
    final static String BASE_URL = "https://api.groq.com/openai/v1";
    final static String API_KEY = "your-api-key";
    final static String MODEL = "openai/gpt-oss-120b";

    public static void main(String[] args) {
        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(API_KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var respuesta = chatModel.chat("Hola, ¿cómo estás?");
        System.out.println(respuesta);
    }
}