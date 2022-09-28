package com.palak.myweatherapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.palak.starwarsdemo.api.ApiClient
import com.palak.starwarsdemo.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder() : GsonBuilder {
        return GsonBuilder()
    }

    @Singleton
    @Provides
    fun provideGson(gsonBuilder: GsonBuilder) : Gson {
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun getApiClient(): ApiClient {
        return ApiClient()
    }

    @Singleton
    @Provides
    fun getRetrofit(apiClient : ApiClient): ApiInterface {
        return apiClient.retrofitService()
    }
}