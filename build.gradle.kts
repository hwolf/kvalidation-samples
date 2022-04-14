import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.20"
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
    id("org.sonarqube") version "3.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation(platform("io.github.hwolf.kvalidation:kvalidation-bom:0.4.1"))
    implementation("io.github.hwolf.kvalidation:kvalidation-core")
    implementation("io.github.hwolf.kvalidation:kvalidation-common")
    implementation("io.github.hwolf.kvalidation:kvalidation-i18n")

    testImplementation(platform("io.strikt:strikt-bom:0.34.1"))
    testImplementation(platform("io.kotest:kotest-bom:5.2.3"))

    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.strikt:strikt-core")
    testImplementation("org.tinylog:tinylog-impl:2.4.1")
    testImplementation("org.tinylog:slf4j-tinylog:2.4.1")
}

sonarqube {
    properties {
        property("sonar.projectKey", "hwolf_kvalidation-samples")
        property("sonar.organization", "hwolf")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.junit.reportPaths", "build/test-results/**/*.xml")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/report.xml")
    }
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
