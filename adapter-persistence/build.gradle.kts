plugins {
    // https://stackoverflow.com/a/64302254/8524651
    // `java-library` // TODO: Should we use the library plugin or the default java plugin?
    java
    `java-test-fixtures`
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":core"))

    // persistence

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // TODO: Probably leading to errors as introducing multiple datasources?
    // implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    // implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    //implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.liquibase:liquibase-core")

    runtimeOnly("com.h2database:h2")
    //runtimeOnly("io.r2dbc:r2dbc-h2")
    runtimeOnly("org.postgresql:postgresql")
    //runtimeOnly("org.postgresql:r2dbc-postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:postgresql")
    //testImplementation("org.testcontainers:r2dbc")


    val queryDslVersion = "4.2.2"
    // TODO cant use querydsl
    // https://stackoverflow.com/a/59951292/8524651
    // Unable to load class 'com.mysema.codegen.model.Type'.
    //implementation("com.querydsl:querydsl-sql:${queryDslVersion}")
    //annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:general")


    testFixturesImplementation("org.springframework.boot:spring-boot-starter")
}
