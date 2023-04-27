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
    "adapter-frontend",
    "adapter-persistence",
    "adapter-amqp",
    "core"
)
