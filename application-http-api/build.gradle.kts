import org.springframework.boot.gradle.tasks.bundling.BootWar

plugins {
    war
    application
    id("org.springframework.boot")
    id("io.spring.dependency-management")

}

// https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#packaging-executable
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveClassifier.set("boot")
    mainClass.set("de.hartz.software.sodevsalaryguide.application.http.api.WebuiApplication")
}

tasks.named<Jar>("jar") {
    archiveClassifier.set("")
}

tasks.named<BootWar>("bootWar") {
    mustRunAfter(project(":adapter-frontend").tasks.getByName("jar"))
    dependsOn(project(":adapter-frontend").tasks.getByName("jar"))
    doFirst {
        delete("${project.layout.buildDirectory.get()}/resources/main/public")
    }
    // project(":application-http-api")
    // from(zipTree(project(":adapter-frontend").layout.buildDirectory.dir("libs/adapter-frontend-0.0.1-SNAPSHOT-plain.jar"))) {
    //     include("public/**")
    // }
    // "../adapter-frontend/src/main/resources"
    // from(zipTree(project(":adapter-frontend"))) {

    copy {
        from("../adapter-frontend/src/main/resources/public/")
        // ${project.layout.buildDirectory.get()}/resources/main/
        into("${project.layout.buildDirectory.get()}/resources/main/public/")
        // into("src/main/resources/public/")
        // include '*.war'
    }

    // from("../adapter-frontend/src/main/resources") {
    //     include("public/**")
    // }
    // into("src/main/resources/") // Destination directory in otherModule
}


springBoot {
    buildInfo {
        properties {
            artifact.set("sodevsalaryguide-webui")
            version.set("1.0.0-alpha")
            group.set("de.hartz.software.sodevsalaryguide")
            name.set("Stackoverflow Salary Guide V2")
        }
    }
}


dependencies {
    // modules
    implementation(project(":core"))
    implementation(project(":adapter-frontend"))
    // annotationProcessor(project(":adapter-frontend"))
    implementation(project(":adapter-persistence"))
    // annotationProcessor(project(":adapter-persistence"))

    implementation(project(":adapter-amqp"))
    annotationProcessor(project(":adapter-amqp"))

    // http
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")

    // https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/boot-features-developing-auto-configuration.html#boot-features-custom-starter-module-autoconfigure
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")

    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    // TODO: add reactive with https://www.baeldung.com/spring-webflux

    // implementation("org.springframework.boot:spring-boot-starter-web-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation(testFixtures(project(":adapter-persistence")))
    testImplementation(testFixtures(project(":core")))
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

// TODÒ: Remove.
// war {
//     with(
//         copySpec({
//             from(zipTree("TODO") {
//             exclude 'WEB-INF/web.xml'
//         })
//     })
// }

// sourceSets {
//     main {
//         resources {
//             srcDir("../B/src/main/resources")
//         }
//     }
//
//     test {
//         resources {
//             srcDir("../B/src/main/resources")
//         }
//     }
// }