plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"

tasks.named<Jar>("jar") {
    archiveClassifier.set("")
}

dependencies {
    implementation(project(":core"))
    implementation("com.rabbitmq:amqp-client:5.16.0")

    implementation("org.springframework.boot:spring-boot-starter-amqp")

}