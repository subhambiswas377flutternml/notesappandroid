package com.aero.notesapp.core

import android.content.Context
import androidx.room.Room
import com.aero.notesapp.core.localdb.LocalDbClient
import com.aero.notesapp.data.datasource.local.AuthLocalDataSource
import com.aero.notesapp.data.datasource.remote.AuthRemoteDataSource
import com.aero.notesapp.data.repositoryimpl.AuthRepositoryImpl
import com.aero.notesapp.domain.repository.AuthRepository
import com.aero.notesapp.domain.usecase.CheckAuthUsecase
import com.aero.notesapp.domain.usecase.LoginUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDbClient(@ApplicationContext appContext: Context): LocalDbClient{
        return Room.databaseBuilder(context = appContext, LocalDbClient::class.java, "notely_db").build()
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(retrofit: Retrofit): AuthRemoteDataSource{
        return retrofit.create(AuthRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(localDbClient: LocalDbClient): AuthLocalDataSource{
        return localDbClient.initAuthDb()
    }


    @Provides
    @Singleton
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource, authLocalDataSource: AuthLocalDataSource): AuthRepository{
        return AuthRepositoryImpl(authRemoteDataSource, authLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUsecase(authRepository: AuthRepository): LoginUsecase{
        return LoginUsecase(authRepository)
    }

    @Provides
    @Singleton
    fun provideCheckAuthUsecae(authRepository: AuthRepository): CheckAuthUsecase{
        return CheckAuthUsecase(authRepository = authRepository)
    }
}