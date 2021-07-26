package com.example.surf_test_app


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        var original = chain.request()
        //fixme add api key to build params
        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter(
                "api_key",
                "6ccd72a2a8fc239b13f209408fc31c33"
            ).build()
        original = original.newBuilder().url(newUrl).build()
        chain.proceed(original)
    }

    private val defaultQueryParameters = Interceptor { chain ->
        var request = chain.request()
        var newUrl =
            chain.request().url.newBuilder().addQueryParameter(
                "language",
                "ru-RU"
            ).build()
        request = request.newBuilder().url(newUrl).build()
        chain.proceed(request)
    }

    //Todo maybe add cache
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(defaultQueryParameters)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val filmsService: TMDbAPI = retrofit.create(TMDbAPI::class.java)
}