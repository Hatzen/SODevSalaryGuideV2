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
    testImplementation("com.github.fridujo:rabbitmq-mock:1.2.0")


    testFixturesImplementation("com.github.fridujo:rabbitmq-mock:1.2.0")
    testFixturesImplementation("com.rabbitmq:amqp-client:5.16.0")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-amqp")
}