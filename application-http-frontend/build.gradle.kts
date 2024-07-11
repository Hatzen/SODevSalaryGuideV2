import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.bundling.BootWar

plugins {
    war
    application
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.siouan.frontend-jdk11") version "6.0.0"
}

// https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#packaging-executable
tasks.named<BootJar>("bootJar") {
    archiveClassifier.set("boot")
    mainClass.set("de.hartz.software.sodevsalaryguide.adapter.frontend.FrontendApplication")
}

tasks.named<Jar>("jar") {
    archiveClassifier.set("")
}

// https://stackoverflow.com/a/62266935/8524651
tasks.named<BootJar>("bootJar") {
    enabled = false
}
tasks.named<Jar>("jar") {
    enabled = true
}

tasks.named<BootWar>("bootWar") {
    // dependsOn(frontend)
}

springBoot {
    buildInfo {
        properties {
            artifact.set("sodevsalaryguide-webui-frontend")
            version.set("1.0.0-alpha")
            group.set("de.hartz.software.sodevsalaryguide")
            name.set("Stackoverflow Salary Guide V2")
        }
    }
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