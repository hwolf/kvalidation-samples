import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlinx:kover:0.6.1")
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.20"
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
    id("org.sonarqube") version "4.0.0.2929"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation(platform("io.github.hwolf.kvalidation:kvalidation-bom:0.5.9"))
    implementation("io.github.hwolf.kvalidation:kvalidation-core")
    implementation("io.github.hwolf.kvalidation:kvalidation-common")
    implementation("io.github.hwolf.kvalidation:kvalidation-i18n")

    testImplementation(platform("io.strikt:strikt-bom:0.34.1"))
    testImplementation(platform("io.kotest:kotest-bom:5.5.5"))

    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.strikt:strikt-core")
    testImplementation("org.tinylog:tinylog-impl:2.6.1")
    testImplementation("org.tinylog:slf4j-tinylog:2.6.1")
}

sonar {
    properties {
        property("sonar.projectKey", "hwolf_kvalidation-samples")
        property("sonar.organization", "hwolf")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.junit.reportPaths", "build/test-results/**/*.xml")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/xml/report.xml")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
