plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation ("org.apache.avro:avro:1.11.0")
	implementation("io.confluent:kafka-avro-serializer:7.3.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

avro {
	setFieldVisibility("PUBLIC") // Enables public setters
	fieldVisibility = "PRIVATE"
}


tasks.named<JavaCompile>("compileJava") {
	sourceSets.getByName("main").java.srcDirs("src/main/java", "build/generated-main-avro-java")
}
//
//tasks.named("compileJava") {
//	dependsOn("generateAvroJava")
//}
