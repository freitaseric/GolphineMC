plugins {
    kotlin("kapt")
    id("com.gradleup.shadow") version "9.3.0"
    id("xyz.jpenilla.run-velocity") version "2.3.1"
}

group = "com.freitaseric.golphinemc"
version = "1.0-SNAPSHOT"

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    kapt("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    compileOnly("net.luckperms:api:5.4")
}

tasks {
    runVelocity {
        velocityVersion("3.4.0-SNAPSHOT")
    }
}
