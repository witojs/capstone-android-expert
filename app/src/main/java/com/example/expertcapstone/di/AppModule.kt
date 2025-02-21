package com.example.expertcapstone.di

import com.example.expertcapstone.core.domain.useCase.GameInteractor
import com.example.expertcapstone.core.domain.useCase.GameUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideGameUseCase(gameInteractor: GameInteractor): GameUseCase
}

