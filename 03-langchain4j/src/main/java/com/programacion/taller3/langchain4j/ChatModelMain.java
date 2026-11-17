package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class ChatModelMain {
    final static String BASE_URL = "https://api.groq.com/openai/v1";
    final static String API_KEY = "";

    // Cambia esto por un modelo conversacional de Groq:
    final static String MODEL = "llama-3.3-70b-versatile";

    public static void main(String[] args) {
        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(API_KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var request = chatModel.generate("Hola, ¿cómo estás?");
        System.out.println(request);
    }
}