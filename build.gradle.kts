plugins {
    java
}

group = "edu.kit.informatik"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(11)
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
