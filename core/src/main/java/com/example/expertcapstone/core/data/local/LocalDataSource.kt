package com.example.expertcapstone.core.data.local

import com.example.expertcapstone.core.data.local.entity.GameEntity
import com.example.expertcapstone.core.data.local.room.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao) {
    fun getAllGames(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getFavoriteGameById(id: Int): Flow<GameEntity> = gameDao.getFavoriteGameById(id)

    suspend fun insertGame(gameEntity: GameEntity) = gameDao.insertGame(gameEntity)

    suspend fun deleteGame(gameEntity: GameEntity) = gameDao.deleteGame(gameEntity)

    fun updateFavoriteGame(gameEntity: GameEntity, newState: Boolean){
        gameEntity.isFavorite = newState
        gameDao.updateFavoriteGame(gameEntity)
    }
}