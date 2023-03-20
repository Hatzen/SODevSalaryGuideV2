plugins {
    id("java")
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":core"))

    // batch
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("com.opencsv:opencsv:4.1")

    testImplementation("org.springframework.batch:spring-batch-test")

}