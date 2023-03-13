plugins {
	java
	war
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
}

java.sourceCompatibility = JavaVersion.VERSION_17
val snippetsDir by extra { file("build/generated-snippets") }
val testcontainersVersion by extra { "1.17.6" }

allprojects {
	group = "de.hartz.software.sodevsalaryguide"
	version = "0.0.1-SNAPSHOT"

	apply(plugin = "java")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	repositories {
		mavenCentral()
	}
	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
		developmentOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	dependencies {
		// base
		compileOnly("org.projectlombok:lombok")
		developmentOnly("org.springframework.boot:spring-boot-devtools")
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
		annotationProcessor("org.projectlombok:lombok")


		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.testcontainers:junit-jupiter")
	}

	dependencyManagement {
		imports {
			mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
		}
	}
}

dependencies {
	// modules
	implementation(project(":core"))
	implementation(project(":adapter-batch"))
	implementation(project(":adapter-persistence"))

	// tools
	/*
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
	implementation("com.okta.spring:okta-spring-boot-starter:3.0.3")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")


	implementation("org.springframework.boot:spring-boot-starter-quartz")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")


	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:elasticsearch")
	 */
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	outputs.dir(snippetsDir)
}
