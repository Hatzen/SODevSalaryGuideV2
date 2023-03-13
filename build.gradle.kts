plugins {
	java
	war
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
	//  id("org.asciidoctor.convert") version "2.2.1"
}

java.sourceCompatibility = JavaVersion.VERSION_17


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
		// testImplementation("org.testcontainers:junit-jupiter")
	}

	/*
	dependencyManagement {
		imports {
			mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
		}
	}
	 */
}

val snippetsDir by extra { file("build/generated-snippets") }
// extra["testcontainersVersion"] = "1.17.6"

dependencies {
	// modules
	implementation(project(":core"))
	implementation(project(":adapter-batch"))
	implementation(project(":adapter-persistence"))
	// implementation(project(":core"))

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

/*
dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}
 */

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	outputs.dir(snippetsDir)
}

/*
tasks.asciidoctor {
	inputs.dir(snippetsDir)
	dependsOn(test)
}
 */