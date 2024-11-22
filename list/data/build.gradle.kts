plugins {
    alias(libs.plugins.repormation.android.library)
}

android {
    namespace = "com.macrosystems.list.data"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.okhttp)
    implementation(libs.bundles.koin)

    implementation(projects.list.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)

}