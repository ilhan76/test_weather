package com.example.myapplication.util.extensions

import com.example.myapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun Retrofit.Builder.setClient() = apply {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addHttpLoggingInterceptor()
        .addQueryInterceptor()
        .build()

    this.client(okHttpClient)
}

fun OkHttpClient.Builder.addHttpLoggingInterceptor() = apply {
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        this.addNetworkInterceptor(loggingInterceptor)
    }
}

fun OkHttpClient.Builder.addQueryInterceptor() = apply {
    val interceptor = Interceptor { chain ->
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .addQueryParameter("units", "metric")
            .addQueryParameter("lang", "en")
            .build()
        request = request.newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }
    this.addInterceptor(interceptor)
}

fun Retrofit.Builder.addJsonConvertor() = apply {
    this.addConverterFactory(GsonConverterFactory.create())
}