plugins {
    id("java")
}

group = "com.uce.edu"
version = "unspecified"

repositories {
    mavenCentral()
}

val langchain4jVersion = "1.23.0"

dependencies {
    // 1. Importar el BOM de LangChain4j para gestionar las versiones de forma centralizada
    implementation(platform("dev.langchain4j:langchain4j-bom:${langchain4jVersion}"))

    // 2. Declarar las dependencias sin especificar la versión (el BOM las autogestiona)
    implementation("dev.langchain4j:langchain4j")
    implementation("dev.langchain4j:langchain4j-embeddings")
    implementation("dev.langchain4j:langchain4j-open-ai")

    // Implementación de logback
    implementation("ch.qos.logback:logback-classic:1.6.6")
}