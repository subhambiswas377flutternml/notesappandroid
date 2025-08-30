package com.aero.notesapp.core

import com.aero.notesapp.data.datasource.remote.AuthRemoteDataSource
import com.aero.notesapp.data.repositoryimpl.AuthRepositoryImpl
import com.aero.notesapp.domain.repository.AuthRepository
import com.aero.notesapp.domain.usecase.LoginUsecase
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

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(retrofit: Retrofit): AuthRemoteDataSource{
        return retrofit.create(AuthRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRepository{
        return AuthRepositoryImpl(authRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUsecase(authRepository: AuthRepository): LoginUsecase{
        return LoginUsecase(authRepository)
    }
}