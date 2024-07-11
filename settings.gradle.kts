pluginManagement {
    plugins {
        id("org.siouan.frontend-jdk11") version "6.0.0"
    }
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "sodevsalaryguide"

include(
    "application-http-api",
    "application-batch-worker-intake",
    "application-http-frontend",
    "adapter-persistence",
    "adapter-amqp",
    "core"
)
