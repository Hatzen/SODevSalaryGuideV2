plugins {
    java
}

dependencies {
    // modules
    implementation(project(":adapter-frontend"))

    // http
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    // TODO: add reactive with https://www.baeldung.com/spring-webflux

    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}
