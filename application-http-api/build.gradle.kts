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
    implementation(project(":core"))
    implementation(project(":adapter-persistence"))
    // Needed for routes and security.
    implementation(project(":application-http-config"))

    implementation(project(":adapter-amqp"))
    annotationProcessor(project(":adapter-amqp"))

    implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-discoveryclient")

    // http
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")

    // https://github.com/MarcGiffing/bucket4j-spring-boot-starter
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.hazelcast:hazelcast")
    implementation("com.giffing.bucket4j.spring.boot.starter:bucket4j-spring-boot-starter:0.12.7")

    // https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/boot-features-developing-auto-configuration.html#boot-features-custom-starter-module-autoconfigure
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")

    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    // TODO: add reactive with https://www.baeldung.com/spring-webflux

    // implementation("org.springframework.boot:spring-boot-starter-web-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation(testFixtures(project(":adapter-persistence")))
    testImplementation(testFixtures(project(":application-http-config")))
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}
