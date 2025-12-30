import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    kotlin("jvm") version "2.3.0" apply false
}

subprojects {
    group = "com.freitaseric.golphinemc"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/") {
            name = "papermc-repo"
        }
        maven("https://repo.opencollab.dev/main/") {
            name = "opencollab-repo"
        }
    }

    val rootName = rootProject.name
    val platformName = project.name
    val projVersion = project.version.toString()

    val finalJarName = "${rootName}_${platformName}_${projVersion}.jar"

    plugins.withId("com.gradleup.shadow") {
        tasks.named("shadowJar", Jar::class) {
            archiveFileName.set(finalJarName)
            archiveClassifier.set("")
        }

        tasks.named("jar") {
            enabled = false
        }
    }

    plugins.withId("fabric-loom") {
        tasks.named("remapJar", AbstractArchiveTask::class) {
            archiveFileName.set(finalJarName)
            archiveClassifier.set("")
        }
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
        if (project.name != "common") {
            "implementation"(project(":common"))
        }
    }

    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(21)
    }
}