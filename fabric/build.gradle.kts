plugins {
    id("fabric-loom") version "1.14-SNAPSHOT"
    id("maven-publish")
}

loom {
    splitEnvironmentSourceSets()

    mods {
        register("golphinemc") {
            sourceSet("main")
            sourceSet("client")
        }
    }
}

fabricApi {
    configureDataGeneration {
        client = true
    }
}

java {
    withSourcesJar()
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")

    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("kotlin_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")

    modImplementation("net.luckperms:api:5.4")
    modImplementation("org.geysermc.floodgate:api:2.2.4-SNAPSHOT")
}

tasks.processResources {
    val pVersion = project.version.toString()
    val pMcVersion = project.property("minecraft_version").toString()
    val pLoaderVersion = project.property("loader_version").toString()
    val pKotlinVersion = project.property("kotlin_loader_version").toString()

    val replaceProperties = mapOf(
        "version" to pVersion,
        "minecraft_version" to pMcVersion,
        "loader_version" to pLoaderVersion,
        "kotlin_loader_version" to pKotlinVersion
    )

    inputs.properties(replaceProperties)

    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand(replaceProperties)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.property("archives_base_name") as String
            from(components["java"])
        }
    }
}
