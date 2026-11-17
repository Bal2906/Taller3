plugins {
    id("java")
}

group = "com.uce.edu"
version = "unspecified"

repositories {
    mavenCentral()
}

// Usa una versión estable existente de LangChain4j
val langchain4jVersion = "0.35.0"

dependencies {
    implementation("dev.langchain4j:langchain4j:${langchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-embeddings:${langchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-open-ai:${langchain4jVersion}")

    //Implemnetacion de logback
    implementation("ch.qos.logback:logback-classic:1.6.6")
}