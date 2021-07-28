package com.example.surf_test_app.di

import com.example.surf_test_app.BuildConfig
import com.example.surf_test_app.TMDbAPI
import com.example.surf_test_app.repository.FilmRepositoryImp
import com.example.surf_test_app.repository.FilmsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
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
        val newUrl =
            chain.request().url.newBuilder().addQueryParameter(
                "language",
                "ru-RU"
            ).build()
        request = request.newBuilder().url(newUrl).build()
        chain.proceed(request)
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(defaultQueryParameters)
        .build()


    @Singleton
    @Provides
    fun provideApi(): TMDbAPI = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TMDbAPI::class.java)


    @Provides
    @Singleton
    fun provideFilmRepository(api: TMDbAPI): FilmsRepository = FilmRepositoryImp(api)


}