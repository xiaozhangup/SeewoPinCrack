plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "me.xiaozhangup.seewopincrack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.zxing:core:3.4.1")
    implementation("com.google.zxing:javase:3.4.1")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}

application {
    mainClass.set("SeewoPinCrack")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "SeewoPinCrack"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.test {
    useJUnitPlatform()
}