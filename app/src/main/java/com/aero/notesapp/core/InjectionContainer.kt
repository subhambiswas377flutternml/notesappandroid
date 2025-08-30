package com.aero.notesapp.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InjectionContainer{
    @Provides
    @Singleton
    fun provideApiClient(): Retrofit{
        return Retrofit.Builder().baseUrl(AppConstants.baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}