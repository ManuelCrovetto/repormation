package com.macrosystems.core.data

import okhttp3.Interceptor
import okhttp3.Response

class RepormationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "token ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(request)
    }
}