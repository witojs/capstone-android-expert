package com.example.expertcapstone.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expertcapstone.core.data.local.room.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase = Room.databaseBuilder(
        context,
        GameDatabase::class.java, "Game.db"
    ).build()

    @Provides
    fun provideGameDao(database: GameDatabase) = database.gameDao()
}