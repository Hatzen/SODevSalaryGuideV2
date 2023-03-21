plugins {
    java
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":core"))

    // persistence
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("io.r2dbc:r2dbc-h2")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")

    // testImplementation("org.testcontainers:postgresql")
    // testImplementation("org.testcontainers:r2dbc")


    val queryDslVersion = "4.2.2"
    // TODO cant use querydsl
    // https://stackoverflow.com/a/59951292/8524651
    // Unable to load class 'com.mysema.codegen.model.Type'.
    //implementation("com.querydsl:querydsl-sql:${queryDslVersion}")
    //annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:general")
}
