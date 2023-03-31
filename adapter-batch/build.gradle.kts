plugins {
    id("java")
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":core"))
    implementation(project(":adapter-amqp"))
    implementation(project(":adapter-persistence"))


    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    // http
    // TODO: Is there a smaller dependency?
    implementation("org.springframework.boot:spring-boot-starter-web")

    // batch
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("com.opencsv:opencsv:4.1")

    testImplementation("org.springframework.batch:spring-batch-test")

    // https://stackoverflow.com/a/60138176/8524651
    testImplementation(testFixtures(project(":adapter-persistence")))
    testImplementation((project(":adapter-persistence")))
}