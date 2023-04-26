pluginManagement {
    plugins {
        id("org.siouan.frontend-jdk11") version "6.0.0"
    }
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "sodevsalaryguide"

include("application-http-api")
include("application-batch-worker-intake")
include("adapter-frontend")
include("adapter-persistence")
include("adapter-amqp")
include("core")
