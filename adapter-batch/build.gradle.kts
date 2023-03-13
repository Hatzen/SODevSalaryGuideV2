plugins {
    id("java")
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"

dependencies {

    // batch
    implementation("org.springframework.boot:spring-boot-starter-batch")

    testImplementation("org.springframework.batch:spring-batch-test")

}