package com.example.expertcapstone.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expertcapstone.core.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(gameEntity: GameEntity)

    @Query("SELECT * FROM game WHERE id = :id")
    fun getFavoriteGameById(id: Int): Flow<GameEntity>

    @Delete
    suspend fun deleteGame(gameEntity: GameEntity)

    @Update
    fun updateFavoriteGame(gameEntity: GameEntity)
}