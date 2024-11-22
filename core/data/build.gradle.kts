plugins {
    alias(libs.plugins.repormation.android.library)
}

android {
    namespace = "com.macrosystems.core.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(libs.okhttp)
    implementation(libs.retrofitConverter)
    implementation(libs.retrofit)

    implementation(projects.core.domain)
}
