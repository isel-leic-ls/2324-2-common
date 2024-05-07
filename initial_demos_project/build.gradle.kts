import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC")
    testImplementation(kotlin("test"))
    implementation(platform("org.http4k:http4k-bom:5.13.9.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-jetty")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.+")
    implementation("org.slf4j:slf4j-simple:2.0.9") // for logging
    implementation("org.postgresql:postgresql:42.+")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    dependsOn("copyRuntimeDependencies")
    manifest {
        attributes["Main-Class"] = "pt.isel.ls.http.HTTPServerKt"
        attributes["Class-Path"] = configurations.runtimeClasspath.get().joinToString(" ") { it.name }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.register<Copy>("copyRuntimeDependencies") {
    into("build/libs")
    from(configurations.runtimeClasspath)
}
