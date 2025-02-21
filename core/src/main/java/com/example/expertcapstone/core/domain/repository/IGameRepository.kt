package com.example.expertcapstone.core.domain.repository

import com.example.expertcapstone.core.data.Resource
import com.example.expertcapstone.core.domain.model.Game
import com.example.expertcapstone.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    suspend fun getGames(): Flow<Resource<List<Game>>>

    suspend fun getDetailGame(id: Int): Flow<Resource<GameDetail>>

    fun getAllGames(): Flow<List<GameDetail>>

    fun getFavoriteGameById(id: Int): Flow<GameDetail>

    suspend fun insertGame(game: GameDetail)

    suspend fun deleteGame(game: GameDetail)

    fun updateFavoriteGame(game: GameDetail, newState: Boolean)
}