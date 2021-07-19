import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.*

plugins {
    kotlin("js") version "1.5.21"
}

group = "kotlin-poc-frontend-react-redux"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.218-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.218-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.0-pre.218-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.4-pre.218-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.0-pre.218-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:5.2.0-pre.218-kotlin-1.5.21")
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
                mode = if(project.hasProperty("prod")) Mode.PRODUCTION else Mode.DEVELOPMENT
            }
        }
        useCommonJs()
    }
}