plugins {
    java
}

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
