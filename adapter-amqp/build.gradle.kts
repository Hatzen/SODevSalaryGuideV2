plugins {
    java
    `java-test-fixtures`
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":core"))
    implementation("com.rabbitmq:amqp-client:5.16.0")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.github.fridujo:rabbitmq-mock:master-SNAPSHOT")

    // TODO: jackson conveter
    implementation("org.springframework.boot:spring-boot-starter-web")
    // testImplementation("com.fasterxml.jackson.core:jackson-dataformat-xml:2.14.2")

    // TODO: use jitpack and try newest version with spring boot 3 support
    testFixturesImplementation("com.github.fridujo:rabbitmq-mock:master-SNAPSHOT")
    // testFixturesImplementation("com.github.fridujo:rabbitmq-mock:1.3.0-SNAPSHOT")
    testFixturesImplementation("com.rabbitmq:amqp-client:5.16.0")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-amqp")
}