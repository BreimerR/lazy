buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }

    val kotlinVersion: String by project

    dependencies {
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}