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
    implementation(project(":adapter-frontend"))
    implementation(project(":adapter-persistence"))
    annotationProcessor(project(":adapter-persistence"))

    // http
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")


    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    // TODO: add reactive with https://www.baeldung.com/spring-webflux

    // implementation("org.springframework.boot:spring-boot-starter-web-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation(testFixtures(project(":adapter-persistence")))
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}
