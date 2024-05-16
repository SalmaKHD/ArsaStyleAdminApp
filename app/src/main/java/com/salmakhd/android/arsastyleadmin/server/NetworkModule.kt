package com.salmakhd.android.arsastyleadmin.server

import com.google.gson.GsonBuilder
import com.salmakhd.android.arsastyleadmin.common.utility.ArsaStyleConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    internal fun loggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.MINUTES)
        .readTimeout(60, TimeUnit.MINUTES)
        .addInterceptor(loggingInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val localDateConvertor =
            GsonBuilder()
                .setLenient()
                .create()
        return Retrofit.Builder()
            .baseUrl(ArsaStyleConstants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(localDateConvertor))
            // .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(
        retrofit: Retrofit
    ): ApiServices =
        retrofit.create(ApiServices::class.java)


    @Provides
    @Singleton
    fun providesNeshanApiService(
        retrofit: Retrofit
    ): NeshanApiServices =
        retrofit.create(NeshanApiServices::class.java)
}