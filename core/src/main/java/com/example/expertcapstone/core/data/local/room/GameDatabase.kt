package com.example.expertcapstone.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expertcapstone.core.data.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}