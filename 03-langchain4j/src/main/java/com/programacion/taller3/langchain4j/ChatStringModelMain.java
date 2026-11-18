package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.atomic.AtomicInteger;

public class ChatStringModelMain {
    public static void main(String[] args) {
        OpenAiStreamingChatModel model = OpenAiStreamingChatModel.builder()
                .apiKey(ChatModelMain.API_KEY)
                .modelName(ChatModelMain.MODEL)
                .baseUrl(ChatModelMain.BASE_URL)
                .logRequests(true)
                .logResponses(true)
                .build();

        System.out.println("Enviando mensaje...");

        // Llamada usando el handler de streaming actualizado de la versión 1.x
        model.chat("Hola, ¿cómo estás?", new MyStreamingChatResponseHandler());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Implementar el StreamingChatResponseHandler de dev.langchain4j.model.chat.response
class MyStreamingChatResponseHandler implements StreamingChatResponseHandler {

    @Override
    public void onPartialResponse(String partialResponse) {
        System.out.print(partialResponse);
    }

    @Override
    public void onCompleteResponse(ChatResponse completeResponse) {
        System.out.println();
        System.out.println("=== Respuesta completa ===");
        System.out.println(completeResponse.aiMessage().text());
    }

    @Override
    public void onError(Throwable error) {
        System.err.println("Error: " + error.getMessage());
        error.printStackTrace();
    }
}