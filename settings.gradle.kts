pluginManagement {
    plugins {
        kotlin("org.siouan.frontend-jdk11") version "6.0.0"
    }
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "backend"

include("adapter-http")
include("adapter-persistence")
include("adapter-batch")
