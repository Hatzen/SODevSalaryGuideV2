import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.siouan.frontend-jdk11") version "6.0.0"
    // id("org.springframework.boot") apply false // Dont apply to not create boot jar
    // id("io.spring.dependency-management") apply false
}

/*
tasks.named<Jar>("jar") {
    archiveClassifier.set("")
}
 */
// https://stackoverflow.com/a/62266935/8524651
tasks.named<BootJar>("bootJar") {
    enabled = false
}
tasks.named<Jar>("jar") {
    enabled = true
    // https://stackoverflow.com/a/70339502/8524651
    // > Entry META-INF/spring/org.springframework.boot.autoconfigure.Autoconfiguration.imports is a duplicate but no duplicate handling strategy has been set. Please refer to https://docs.gradle.org/7.6.1/dsl/org.gradle.api.tasks.Copy.html#org.gradle.api.tasks.Copy:duplicatesStrategy for details.
    // duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    // Run npm build and move build files to resources/public
    // dependsOn("publish")
}

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