
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.hujiayucc.hook"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hujiayucc.hook"
        minSdk = 26
        targetSdk = 35
        versionCode = 99999
        versionName = "9.9.9"

        buildConfigField("String", "SERVICE_NAME", ""com.hujiayucc.hook.service.SkipService"")
        buildConfigField("String", "TAG", ""Faker"")
        buildConfigField("String", "CHANNEL_ID", ""auto_faker"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            versionNameSuffix = "-release"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            versionNameSuffix = "-debug"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    applicationVariants.all {
        outputs.all { output ->
            val baseName = "Faker_$versionName"
            when (output) {
                is com.android.build.gradle.internal.api.ApkVariantOutputImpl -> {
                    output.outputFileName = "$baseName.apk"
                    true
                }
                else -> {false}
            }
        }
    }
}

dependencies {
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("com.github.yalantis:ucrop:2.2.6")
    implementation("com.highcapable.yukihookapi:api:1.2.1")
    ksp("com.highcapable.yukihookapi:ksp-xposed:1.2.1")
}
