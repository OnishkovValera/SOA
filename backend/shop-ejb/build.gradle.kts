plugins {
    kotlin("jvm")
    id("java-library")
}

group = "org.onishkovValery"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    compileOnly("org.slf4j:slf4j-api:2.0.16")
    implementation(project(":shared-libs"))
}

tasks.jar {
    archiveFileName.set("shop-ejb.jar")
}
