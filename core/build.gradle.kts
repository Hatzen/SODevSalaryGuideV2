plugins {
    java
}

group = "de.hartz.software.sodevsalaryguide"
version = "0.0.1-SNAPSHOT"


dependencies {
    // modules
    implementation(project(":adapter-frontend"))
    implementation(project(":adapter-http-api"))
    implementation(project(":adapter-batch"))
    implementation(project(":adapter-persistence"))
}