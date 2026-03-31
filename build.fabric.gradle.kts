import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    id("net.fabricmc.fabric-loom") version "1.14-SNAPSHOT" apply false
    id("net.fabricmc.fabric-loom-remap") version "1.14-SNAPSHOT" apply false
    id("me.modmuss50.mod-publish-plugin") version "1.1.0"
}

val unobfuscated = project.name.startsWith("26")
println("unobfuscated minecraft? $unobfuscated")

apply(plugin = if (unobfuscated) "net.fabricmc.fabric-loom" else "net.fabricmc.fabric-loom-remap")

val loomExt = project.extensions.getByType<LoomGradleExtensionAPI>()

base {
    archivesName.set("${property("mod.id")}")
}

val requiredJava = when {
    stonecutter.eval(stonecutter.current.version, ">=1.20.6") -> JavaVersion.VERSION_21
    stonecutter.eval(stonecutter.current.version, ">=1.18") -> JavaVersion.VERSION_17
    stonecutter.eval(stonecutter.current.version, ">=1.17") -> JavaVersion.VERSION_16
    else -> JavaVersion.VERSION_1_8
}

repositories {
    maven("https://maven.parchmentmc.org/")
}

dependencies {
    add("minecraft", "com.mojang:minecraft:${property("minecraft.version")}")
    
    if (!unobfuscated) {
        add("mappings", loomExt.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${property("parchment.version")}@zip")
        })
    }
    
    implementation("net.fabricmc:fabric-loader:${property("loader.version")}")
    
    implementation("net.fabricmc.fabric-api:fabric-api:${property("api.version")}")
}

loomExt.apply {
    accessWidenerPath.set(project.file("src/main/resources/${property("mod.id")}.accesswidener"))

    if (!unobfuscated) {
        mixin {
            useLegacyMixinAp.set(true)
            defaultRefmapName.set("${property("mod.id")}.refmap.json")
        }
    }
}

java {
    withSourcesJar()
    targetCompatibility = requiredJava
    sourceCompatibility = requiredJava
}

version = "fabric-${property("minecraft.version")}-${property("mod.version")}"
group = "${property("mod.group")}"

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

        filesMatching("fabric.mod.json") {
            expand(props)
        }
    }

    named<org.gradle.jvm.tasks.Jar>("jar") {
        exclude(".cache")
    }

    if (!unobfuscated) {
        named<org.gradle.jvm.tasks.Jar>("remapJar") {
            from(rootProject.file("LICENSE"))
        }
        named<org.gradle.jvm.tasks.Jar>("remapSourcesJar") {
            from(rootProject.file("LICENSE"))
        }
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        
        val targetJar = if (unobfuscated) named<org.gradle.jvm.tasks.Jar>("jar") else named<org.gradle.jvm.tasks.Jar>("remapJar")
        val targetSourcesJar = if (unobfuscated) named<org.gradle.jvm.tasks.Jar>("sourcesJar") else named<org.gradle.jvm.tasks.Jar>("remapSourcesJar")
        
        from(targetJar.map { it.archiveFile }, targetSourcesJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.dir("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

publishMods {
    dryRun = true

    val targetPublishJar = if (unobfuscated) tasks.named<org.gradle.jvm.tasks.Jar>("jar") else tasks.named<org.gradle.jvm.tasks.Jar>("remapJar")
    file.set(targetPublishJar.flatMap { it.archiveFile })
    
    displayName = "${property("mod.name")} ${property("mod.version")} (Fabric ${property("minecraft.version")})"
    version = "Fabric-${property("minecraft.version")}-${property("mod.version")}"
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = STABLE
    modLoaders.add("fabric")

    modrinth {
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        projectId = "${property("publish.modrinth.id")}"

        minecraftVersions.addAll(property("publish.version_range").toString().split(' '))

        requires {
            slug = "fabric-api"
        }

        announcementTitle = "Download from Modrinth"
    }

    curseforge {
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        projectId = "${property("publish.curseforge.id")}"

        clientRequired = true
        serverRequired = false
        javaVersions.add(requiredJava)
        minecraftVersions.addAll(property("publish.version_range").toString().split(' '))

        requires {
            slug = "fabric-api"
        }

        projectSlug = "${property("mod.id")}"
        announcementTitle = "Download from CurseForge"
    }

    github {
        accessToken = providers.environmentVariable("GITHUB_TOKEN")
        repository = "${property("mod.author")}/${property("mod.name")}"

        val targetSources = if (unobfuscated) tasks.named<org.gradle.jvm.tasks.Jar>("sourcesJar") else tasks.named<org.gradle.jvm.tasks.Jar>("remapSourcesJar")
        additionalFiles.from(targetSources.flatMap { it.archiveFile })
        
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

        content = changelog.map { "# Check out ${project.property("mod.name")} ${project.property("mod.version")}!\n$it" }

        username = "suikabot"

        style {
            look = "MODERN"
            thumbnailUrl = "https://cdn.modrinth.com/data/${project.property("publish.modrinth.id")}/icon.png"
        }
    }
}