pluginManagement {
    plugins {
        id("org.siouan.frontend-jdk11") version "6.0.0"
    }
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "backend"

include("adapter-http-api")
include("adapter-frontend")
include("adapter-persistence")
include("adapter-batch")
include("adapter-amqp")
include("core")
