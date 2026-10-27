plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.knuddels:jtokkit:1.1.0")
}

tasks.test {
    useJUnitPlatform()
}