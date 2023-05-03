plugins {
    java
    id("org.siouan.frontend-jdk11") version "6.0.0"
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

/*
tasks.named<Jar>("jar") {
    archiveClassifier.set("")
}
 */

dependencies {
    // http
    implementation("org.springframework.boot:spring-boot-starter-web")
}

// https://github.com/siouan/frontend-gradle-plugin/blob/master/examples/multi-projects-applications-shared-distributions/npm-frontend/build.gradle
frontend {
    nodeVersion.set("14.17.3")
    assembleScript.set("run build")
    publishScript.set("run publish")
    // cleanScript = "run clean"
    // checkScript = "run check"
    packageJsonDirectory.set(File(projectDir, "src/main/frontend"))
    verboseModeEnabled.set(true)
}