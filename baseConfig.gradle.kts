// import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
	java
	//kotlin("jvm") version ("1.3.21")
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
}

apply(plugin = "io.spring.dependency-management")

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"
// java.sourceCompatibility = JavaVersion.VERSION_17

/*
// https://stackoverflow.com/a/58667991/8524651
// repositories, dependencies, etc...
val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

 */
allprojects {
	repositories {
		mavenCentral()
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
extra["testcontainersVersion"] = "1.17.6"

dependencies {
	// base
	// compileOnly("org.projectlombok:lombok")
	// developmentOnly("org.springframework.boot:spring-boot-devtools")
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

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	outputs.dir(snippetsDir)
}