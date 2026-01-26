plugins {
	kotlin("jvm") version "2.1.0"
	kotlin("kapt") version "2.1.0"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "org.onishkovValery"
version = "0.0.1-SNAPSHOT"
description = "vehicle-service"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":shared-libs"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.liquibase:liquibase-core")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")
	implementation("org.mapstruct:mapstruct:1.6.3")
	implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
	kapt("org.mapstruct:mapstruct-processor:1.6.3")

	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")

}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2025.0.0")
	}
}
kotlin {
    jvmToolchain(17)
    compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

kapt {
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
