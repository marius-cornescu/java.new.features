
val slf4jVs = "2.0.16"
val log4jVs = "2.24.2"
val lombokVs = "1.18.36"
val junitJupiterVs = "5.11.3"

plugins {
    id("java")
    id("io.freefair.lombok") version "9.1.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
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
    testCompileOnly("org.projectlombok:lombok:$lombokVs")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
