plugins {
    application
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}
// https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#packaging-executable
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveClassifier.set("boot")
    mainClass.set("de.hartz.software.sodevsalaryguide.application.batch.worker.intake.InputCsvWorkerBatchApplication")
}

tasks.named<Jar>("jar") {
    archiveClassifier.set("")
}

springBoot {
    buildInfo {
        properties {
            artifact.set("sodevsalaryguide-intake-worker")
            version.set("1.0.0-alpha")
            group.set("de.hartz.software.sodevsalaryguide")
            name.set("Stackoverflow Salary Guide V2 Worker Intake")
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":adapter-amqp"))
    annotationProcessor(project(":adapter-amqp"))
    implementation(project(":adapter-persistence"))
    annotationProcessor(project(":adapter-persistence"))

    implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-discoveryclient")

    // http
    // TODO: Is there a smaller dependency?
    implementation("org.springframework.boot:spring-boot-starter-web")

    // batch
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("com.opencsv:opencsv:5.7.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")

    // TODO: Centralize
    testImplementation("com.maciejwalkowiak.spring:wiremock-spring-boot:2.1.2")

    testImplementation(testFixtures(project(":core")))

    testImplementation(testFixtures(project(":adapter-amqp")))
    // https://stackoverflow.com/a/60138176/8524651
    testImplementation(testFixtures(project(":adapter-persistence")))
    testImplementation(testFixtures(project(":adapter-amqp")))
}