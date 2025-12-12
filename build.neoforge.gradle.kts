plugins {
    id("dev.architectury.loom") version "1.13-SNAPSHOT"
    id("me.modmuss50.mod-publish-plugin") version "1.1.0"
}

base {
    archivesName = "${property("mod.id")}"
}

val requiredJava = when {
    stonecutter.eval(stonecutter.current.version, ">=1.20.6") -> JavaVersion.VERSION_21
    stonecutter.eval(stonecutter.current.version, ">=1.18") -> JavaVersion.VERSION_17
    stonecutter.eval(stonecutter.current.version, ">=1.17") -> JavaVersion.VERSION_16
    else -> JavaVersion.VERSION_1_8
}

repositories {
    maven("https://maven.parchmentmc.org/")
    maven("https://maven.neoforged.net/releases/")
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft.version")}")
    mappings(loom.layered() {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${property("parchment.version")}@zip")
    })
    neoForge("net.neoforged:neoforge:${property("api.version")}")
}

sourceSets {
    main {
        resources.srcDir(project.file("src/generated/resources"))
    }
}

loom {
    mixin {
        useLegacyMixinAp = true
        defaultRefmapName = "${property("mod.id")}.refmap.json"
    }

    runs {
        register("datagen") {
            if (stonecutter.eval(stonecutter.current.version, ">=1.21.4")) serverData() else data()
            programArgs("--all")
            programArgs("--mod", "${property("mod.id")}")
            programArgs("--output", project.file("src/generated/resources").absolutePath)
            programArgs("--existing", project.file("src/main/resources").absolutePath)
        }
    }
}

java {
    withSourcesJar()

    targetCompatibility = requiredJava
    sourceCompatibility = requiredJava

    version = "neoforge-${property("minecraft.version")}-${property("mod.version")}"
    group = "${property("mod.group")}"
}

tasks {
    processResources {
        val props = mapOf(
            "java_version" to project.property("java.version"),
            "minecraft_version" to project.property("minecraft.version"),
            "minecraft_version_range" to project.property("minecraft.version_range"),
            "api_version" to project.property("api.version"),
            "api_version_range" to project.property("api.version_range"),
            "loader_version" to project.property("loader.version"),
            "loader_version_range" to project.property("loader.version_range"),
            "mod_id" to project.property("mod.id"),
            "mod_name" to project.property("mod.name"),
            "mod_version" to project.property("mod.version"),
            "mod_author" to project.property("mod.author"),
            "mod_group" to project.property("mod.group"),
            "mod_description" to project.property("mod.description"),
            "mod_license" to project.property("mod.license")
        )
        inputs.properties(props)

        filesMatching(listOf("META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
            expand(props)
        }
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(remapJar.map { it.archiveFile }, remapSourcesJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }

    jar {
        exclude(".cache")
    }

    remapJar {
        from(rootProject.file("LICENSE"))
    }

    remapSourcesJar {
        from(rootProject.file("LICENSE"))
    }
}

publishMods {
    dryRun = true

    file = tasks.remapJar.map { it.archiveFile.get() }
    displayName = "${property("mod.name")} ${property("mod.version")} (NeoForge ${property("minecraft.version")})"
    version = "NeoForge-${property("minecraft.version")}-${property("mod.version")}"
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = STABLE
    modLoaders.add("neoforge")

    modrinth {
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        projectId = "${property("publish.modrinth.id")}"

        minecraftVersions.addAll(property("publish.version_range").toString().split(' '))

        announcementTitle = "Download from Modrinth"
    }

    curseforge {
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        projectId = "${property("publish.curseforge.id")}"

        clientRequired = true
        serverRequired = false
        javaVersions.add(requiredJava)
        minecraftVersions.addAll(property("publish.version_range").toString().split(' '))

        projectSlug = "${property("mod.id")}"
        announcementTitle = "Download from CurseForge"
    }

    github {
        accessToken = providers.environmentVariable("GITHUB_TOKEN")
        repository = "${property("mod.author")}/${property("mod.name")}"

        additionalFiles.from(tasks.remapSourcesJar.map { it.archiveFile.get() })
        displayName = "${property("mod.version")}"
        version = "${property("mod.version")}"
        tagName = "${property("mod.version")}"
        commitish = "master"

        allowEmptyFiles = true

        announcementTitle = "Download from GitHub"
    }

    discord {
        webhookUrl = providers.environmentVariable("DISCORD_WEBHOOK")
        dryRunWebhookUrl = providers.environmentVariable("DISCORD_WEBHOOK_DRY_RUN")

        content = changelog.map { "# Check out ${project.property("mod.name")} ${project.property("mod.version")}!\n" + it}

        username = "yuribot"

        style {
            look = "MODERN"
            thumbnailUrl = "https://cdn.modrinth.com/data/${project.property("publish.modrinth.id")}/icon.png"
        }
    }
}