plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
        pod("AEPRulesEngine")
        pod("AEPServices"){
            extraOpts = listOf("-compiler-option","fmodules")
        }
        pod("AEPCore"){
            extraOpts = listOf("-compiler-option","fmodules")
        }

        pod("AEPLifecycle"){
            extraOpts = listOf("-compiler-option","fmodules")

        }
        pod("AEPIdentity"){
            extraOpts = listOf("-compiler-option","fmodules")

        }
        pod("AEPSignal"){
            extraOpts = listOf("-compiler-option","fmodules")

        }
        pod("AEPEdge"){
            extraOpts = listOf("-compiler-option","fmodules")

        }
        pod("AEPEdgeIdentity"){
            extraOpts = listOf("-compiler-option","fmodules")
        }
    }
    
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.rf.pod_test_04_6"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}