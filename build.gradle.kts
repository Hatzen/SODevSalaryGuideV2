import java.io.FileInputStream
import java.util.*

plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
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
        maven(url = "https://jitpack.io")
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
        annotationProcessor("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.5"))

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude("org.junit.vintage", "junit-vintage-engine")
        }
        testImplementation("org.testcontainers:junit-jupiter")


        implementation("org.mapstruct:mapstruct:1.5.3.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            // TODO: can we get the versions from spring boot bom? https://spring.io/projects/spring-cloud
            val springCloudVersion = "2023.0.2"
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
            mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.test {
        outputs.dir(snippetsDir)
    }
}

dependencies {
    // modules
    //implementation(project(":core"))
    //implementation(project(":adapter-batch"))
    //implementation(project(":adapter-persistence"))

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


tasks.register("incrementVersionCode") {
    doLast {
        val versionPropsFile = file("src/main/env/example.env")
        if (versionPropsFile.canRead()) {
            val versionProps = Properties()

            versionProps.load(FileInputStream(versionPropsFile))

            val key = "CONTAINER_INCREMENT_VERSION"

            val code = Integer.parseInt(versionProps[key].toString()) + 1

            versionProps[key] = code.toString()
            versionProps.store(versionPropsFile.writer(), null)
            // Write version so it can be consumed
            println(code)
        } else {
            throw GradleException("Could not read version.properties!")
        }
    }
}