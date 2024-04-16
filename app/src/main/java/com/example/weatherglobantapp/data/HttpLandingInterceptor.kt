package com.example.weatherglobantapp.data

import okhttp3.Interceptor
import okhttp3.Response

class HttpLandingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val urlBuilder = chainRequest.url.newBuilder()
            .addQueryParameter("appid", ApiService.API_KEY)
            .build()

        val modifiedRequest = chainRequest.newBuilder().url(urlBuilder).build()

        return chain.proceed(modifiedRequest)
    }
}