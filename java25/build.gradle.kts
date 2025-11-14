val slf4jVs = "2.0.16"
val log4jVs = "2.24.2"
val lombokVs = "1.18.42"
val junitJupiterVs = "5.11.3"
val assertjVs = "3.27.6"

plugins {
    id("java")
    id("io.freefair.lombok") version "9.1.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // LOGGING
    implementation("org.slf4j:slf4j-api:$slf4jVs")
    implementation("org.slf4j:slf4j-log4j12:$slf4jVs")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVs")

    compileOnly("org.projectlombok:lombok:$lombokVs")
    annotationProcessor("org.projectlombok:lombok:$lombokVs")

    // TESTING
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVs")
    testImplementation("org.assertj:assertj-core:$assertjVs")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testCompileOnly("org.projectlombok:lombok:$lombokVs")
}

tasks.test {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
