import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation(platform("io.github.hwolf.kvalidation:kvalidation-bom:0.3.2"))
    implementation("io.github.hwolf.kvalidation:kvalidation-core")
    implementation("io.github.hwolf.kvalidation:kvalidation-common")
    implementation("io.github.hwolf.kvalidation:kvalidation-i18n")

    testImplementation(platform("io.strikt:strikt-bom:0.34.1"))
    testImplementation(platform("io.kotest:kotest-bom:5.1.0"))

    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.strikt:strikt-core")
    testImplementation("org.tinylog:tinylog-impl:2.4.1")
    testImplementation("org.tinylog:slf4j-tinylog:2.4.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
