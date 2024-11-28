package com.macrosystems.core.data.di

import com.macrosystems.core.data.BuildConfig
import com.macrosystems.core.domain.RemoteGitHubDataSource
import com.macrosystems.core.data.networking.RemoteGitHubDataSourceImpl
import com.macrosystems.core.data.networking.RepormationInterceptor
import com.macrosystems.core.data.api.UserGithubAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val coreDataModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()

            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(RepormationInterceptor())
            .build()

    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())

            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())

            .build()
    }
    single {
        get<Retrofit>().create(UserGithubAPI::class.java)
    }
    singleOf(::RemoteGitHubDataSourceImpl).bind<RemoteGitHubDataSource>()
}