package com.nab.assignment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nab.assignment.BuildConfig
import com.nab.assignment.network.WeatherService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Lan Tran on 1/31/21.
 */

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesGson() : Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


    @Provides
    @Singleton
    fun providesCoreRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BuildConfig.ENDPOINT)
            .build()

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}