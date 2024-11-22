plugins {
    alias(libs.plugins.repormation.android.library)
}

android {
    namespace = "com.macrosystems.detail.data"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.bundles.koin)

    implementation(projects.detail.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}