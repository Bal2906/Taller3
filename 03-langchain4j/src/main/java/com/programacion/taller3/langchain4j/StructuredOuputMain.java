package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

record Persona(String nombre, String correo, String telefono, String ciudad, String pais) {}

interface Extractor {
    @UserMessage("""
            Extraee la informacion de contacto del siguiente texto en formato JSON: {{texto}}
            """)
    String extraerInformacionDeContacto(@V("texto") String texto);
}

public class StructuredOuputMain {
    static void main() {
        OpenAiChatModel chatmodel = OpenAiChatModel.builder()
                .apiKey(ChatModelMain.API_KEY)
                .modelName(ChatModelMain.MODEL)
                .baseUrl(ChatModelMain.BASE_URL)
                .logRequests(true)
//                .logResponses(true)
                .build();

        var extractor = AiServices.builder(Extractor.class)
                .chatModel(chatmodel)
                .build();

        var persona = extractor.extraerInformacionDeContacto("""
                Hola mi nombre es Juan Pérez y mi correo es juan.perez@gmail.com, mi telefono es 123456789,
                vivo en la ciudad de Asuncion, Paraguay.
                """);
        System.out.println(persona);
    }
}
