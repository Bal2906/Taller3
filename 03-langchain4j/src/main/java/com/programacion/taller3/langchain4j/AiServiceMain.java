package com.programacion.taller3.langchain4j;

import com.programacion.taller3.langchain4j.utils.AsistenteProgramacion;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;

public class AiServiceMain {
    static void main() {
        OpenAiChatModel chatmodel = OpenAiChatModel.builder()
                .apiKey(ChatModelMain.API_KEY)
                .modelName(ChatModelMain.MODEL)
                .baseUrl(ChatModelMain.BASE_URL)
                .logRequests(true)
//                .logResponses(true)
                .build();

        var asistente = AiServices.create(AsistenteProgramacion.class, chatmodel);

        var respuesta = asistente.responderPregunta("Hola como estas?");
        System.out.println(respuesta);

        System.out.println("------------------------------------------");

        String contexto = """
                            Para recibir el titulo profesional de ingenieria en la universidad central del ecuador son:
                            1. kjasndnslkdlks
                            2. jndskjasndasdj
                            3. kjsdkjsdjaksdj
                            """;

        var respuesta2 = asistente.consultar("Cuales son los requisitos de titulacion?", contexto);
        System.out.println(respuesta2);
    }
}
