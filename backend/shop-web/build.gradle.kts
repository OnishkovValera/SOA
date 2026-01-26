plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("war")
}

group = "org.onishkovValery"
version = "0.0.1-SNAPSHOT"
description = "shop-web"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	all {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.data:spring-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	providedCompile("org.jetbrains.kotlin:kotlin-reflect")
	providedCompile(project(":shared-libs"))
	providedCompile(project(":shop-ejb"))
	providedCompile("org.slf4j:slf4j-api:2.0.16")
	providedCompile("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.war {
	archiveFileName.set("shop-web.war")
}

tasks.bootWar {
	enabled = false
	archiveFileName.set("shop-web.war")
}

