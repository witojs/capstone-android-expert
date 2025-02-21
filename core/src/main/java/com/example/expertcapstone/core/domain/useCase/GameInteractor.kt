package com.example.expertcapstone.core.domain.useCase

import com.example.expertcapstone.core.data.Resource
import com.example.expertcapstone.core.domain.model.Game
import com.example.expertcapstone.core.domain.model.GameDetail
import com.example.expertcapstone.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: IGameRepository) :
    GameUseCase {
    override suspend fun getGames(): Flow<Resource<List<Game>>> = gameRepository.getGames()

    override suspend fun getDetailGame(id: Int): Flow<Resource<GameDetail>> =
        gameRepository.getDetailGame(id)

    //for favorite dynamic module
    override fun getAllGames(): Flow<List<GameDetail>> = gameRepository.getAllGames()

    //for detail and favorite dynamic module
    override fun getFavoriteGameById(id: Int): Flow<GameDetail> =
        gameRepository.getFavoriteGameById(id)

    override suspend fun insertGame(game: GameDetail) = gameRepository.insertGame(game)

    override suspend fun deleteGame(game: GameDetail) = gameRepository.deleteGame(game)
    override fun updateFavoriteGame(game: GameDetail, newState: Boolean) =
        gameRepository.updateFavoriteGame(game, newState)
}