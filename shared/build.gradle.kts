plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
    id("com.vanniktech.maven.publish") version "0.28.0"
}

group = "com.longarinas.ktor.kmp"
version = "0.0.1"


publishing {
    publications {
        publications.withType<MavenPublication> {

            pom {
                name.set("KMP-Ktor")
                description.set("Ktor in a KMP implementation.")
                url.set("https://github.com/mkilmerr/Ktor-KMP")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/mkilmerr/Ktor-KMP")
                }

                developers {
                    developer {
                        id.set("longarinas")
                        name.set("longarinas")
                        email.set("marcoskilmer1@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/mkilmerr/Ktor-KMP")
                    developerConnection.set("scm:git:ssh://github.com/mkilmerr/Ktor-KMP")
                    url.set("https://github.com/mkilmerr/Ktor-KMP")
                }
            }
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.example.kmp_ktor"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
