package com.example.weatherglobantapp.data.api

import okhttp3.Interceptor
import okhttp3.Response

class HttpLandingInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val urlBuilder = chainRequest.url.newBuilder()
            .build()

        val modifiedRequest = chainRequest.newBuilder().url(urlBuilder).build()

        return chain.proceed(modifiedRequest)
    }
}