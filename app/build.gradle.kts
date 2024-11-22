plugins {
    alias(libs.plugins.repormation.android.application.compose)
}

android {
    namespace = "com.macrosystems.repormation"

}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.gson)

    //Project modules
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)
    implementation(projects.list.data)
    implementation(projects.list.domain)
    implementation(projects.list.presentation)
    implementation(projects.detail.data)
    implementation(projects.detail.domain)
    implementation(projects.detail.presentation)
}