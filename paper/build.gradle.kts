plugins {
    id("com.gradleup.shadow") version "9.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("org.geysermc.floodgate:api:2.2.4-SNAPSHOT")
    compileOnly("net.luckperms:api:5.4")
}

tasks {
    runServer {
        minecraftVersion("1.21.11")
    }
}

tasks.processResources {
    val props = mapOf("version" to version)

    inputs.properties(props)
    filteringCharset = "UTF-8"

    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}
