package com.example.expertcapstone.di

import com.example.expertcapstone.core.domain.useCase.GameUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependency {
    fun provideGameUseCase(): GameUseCase
}