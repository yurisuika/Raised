pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
        maven("https://files.minecraftforge.net/maven/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.kikugie.dev/snapshots/")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7.11"
}

stonecutter {
    create(rootProject) {
        fun match(version: String, vararg loaders: String) = loaders.forEach {
            loader -> version("$version-$loader", version).buildscript = "build.$loader.gradle.kts"
        }

        match("1.21.11", "fabric", "neoforge")
        match("1.21.10", "fabric", "forge", "neoforge")
        match("1.21.8", "fabric", "forge", "neoforge")
        match("1.21.5", "fabric", "forge", "neoforge")
        match("1.21.4", "fabric", "forge", "neoforge")
        match("1.21.3", "fabric", "forge", "neoforge")
        match("1.21.1", "fabric", "forge", "neoforge")
        match("1.20.6", "fabric", "forge", "neoforge")
        match("1.20.4", "fabric", "forge", "neoforge")
        match("1.20.2", "fabric", "forge", "neoforge")
        match("1.20.1", "fabric", "forge")
        match("1.19.4", "fabric", "forge")
        match("1.19.3", "fabric", "forge")
        match("1.19.2", "fabric", "forge")
        match("1.19", "fabric", "forge")
        match("1.18.2", "fabric", "forge")
        match("1.17.1", "fabric", "forge")
        match("1.16.5", "fabric", "forge")

        vcsVersion = "1.21.11-fabric"
    }
}

rootProject.name = "Raised"