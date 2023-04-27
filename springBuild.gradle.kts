plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

// TODO: outsource so that e.g. core module dont gain spring dependencies
java.sourceCompatibility = JavaVersion.VERSION_17
val snippetsDir by extra { file("build/generated-snippets") }
val testcontainersVersion by extra { "1.17.6" }

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
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
    annotationProcessor("org.projectlombok:lombok") // compileOnly
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    annotationProcessor("org.springframework.boot:spring-boot-devtools") // developmentONly
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.5"))

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage", "junit-vintage-engine")
    }
    testImplementation("org.testcontainers:junit-jupiter")


    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
}