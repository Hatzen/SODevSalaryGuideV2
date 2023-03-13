plugins {
    java
    id("org.siouan.frontend-jdk11") version "6.0.0"

    //id("org.springframework.boot") version "3.0.4"
    //id("io.spring.dependency-management") version "1.1.0"
}
// apply("../baseConfig.gradle.kts")
//apply(plugin = "io.spring.dependency-management")

//group = "de.hartz.software.sodevsalaryguide"
// version = "0.0.1-SNAPSHOT"

dependencies {

    // http
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

// https://github.com/siouan/frontend-gradle-plugin/blob/master/examples/multi-projects-applications-shared-distributions/npm-frontend/build.gradle
frontend {
    nodeVersion.set("14.17.3")
    assembleScript.set("run build")
    // cleanScript = "run clean"
    // checkScript = "run check"
    packageJsonDirectory.set(File(projectDir, "src/main/frontend"))
    verboseModeEnabled.set(true)
}