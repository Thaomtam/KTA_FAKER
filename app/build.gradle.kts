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
        //noinspection EditedTargetSdkVersion
        targetSdk = 35
        versionCode = 99999
        versionName = "9.9.9"

        buildConfigField("String", "SERVICE_NAME", "\"com.hujiayucc.hook.service.SkipService\"")
        buildConfigField("String", "TAG", "\"Fuck AD\"")
        buildConfigField("String", "CHANNEL_ID", "\"auto_skip1\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            // 开启资源压缩
            isShrinkResources = true
            // 版本后缀
            versionNameSuffix = "-release"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = true
            // 开启资源压缩
            isShrinkResources = true
            // 版本后缀
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
            val baseName = "Fuck AD_$versionName"
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
    //noinspection GradleDependency
    implementation("com.google.android.material:material:1.11.0")
    // implementation("com.github.duanhong169:colorpicker:1.1.6")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    //noinspection GradleDependency
    implementation("androidx.activity:activity-ktx:1.3.1")
    // implementation("androidx.fragment:fragment-ktx:1.3.6")
    implementation("com.github.yalantis:ucrop:2.2.6")

    // 基础依赖
    implementation("com.highcapable.yukihookapi:api:1.2.1")
    // ❗作为 Xposed 模块使用务必添加，其它情况可选
    compileOnly("de.robv.android.xposed:api:82")
    // ❗作为 Xposed 模块使用务必添加，其它情况可选
    ksp("com.highcapable.yukihookapi:ksp-xposed:1.2.1")
}
