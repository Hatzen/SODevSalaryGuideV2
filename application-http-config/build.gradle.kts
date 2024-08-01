plugins {
    war
    application
    `java-test-fixtures`
    id("java-library") // Needed for api dependencies. https://stackoverflow.com/a/74276456/8524651
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

// https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#packaging-executable
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveClassifier.set("boot")
    mainClass.set("de.hartz.software.sodevsalaryguide.application.http.config.ConfigServerApplication")
}

tasks.named<Jar>("jar") {
    archiveClassifier.set("")
}

springBoot {
    buildInfo {
        properties {
            artifact.set("sodevsalaryguide-config-server")
            version.set("1.0.0-alpha")
            group.set("de.hartz.software.sodevsalaryguide")
            name.set("Stackoverflow Salary Guide V2")
        }
    }
}


dependencies {
    // modules
    implementation(project(":core"))

    // http
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")
    api("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-discoveryclient")
    implementation("org.springframework.cloud:spring-cloud-config-server")

    testFixtures("org.springframework.boot:spring-boot-core")
}
