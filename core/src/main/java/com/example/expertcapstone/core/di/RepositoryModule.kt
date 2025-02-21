package com.example.expertcapstone.core.di

import com.example.expertcapstone.core.data.GameRepository
import com.example.expertcapstone.core.data.local.LocalDataSource
import com.example.expertcapstone.core.data.remote.RemoteDataSource
import com.example.expertcapstone.core.domain.repository.IGameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): IGameRepository = GameRepository(remoteDataSource, localDataSource)

}