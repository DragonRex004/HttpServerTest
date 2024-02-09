plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "de.dragonrex"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.11")
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "de.dragonrex.HttpServerTest"
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
