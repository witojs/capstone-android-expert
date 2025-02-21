package com.example.expertcapstone.core.data

import com.example.expertcapstone.core.data.local.LocalDataSource
import com.example.expertcapstone.core.data.remote.RemoteDataSource
import com.example.expertcapstone.core.data.remote.network.ApiResponse
import com.example.expertcapstone.core.domain.model.Game
import com.example.expertcapstone.core.domain.model.GameDetail
import com.example.expertcapstone.core.domain.repository.IGameRepository
import com.example.expertcapstone.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    IGameRepository {
    override suspend fun getGames(): Flow<Resource<List<Game>>> = flow {
        emit(Resource.Loading())
        try {
            when (val apiResponse = remoteDataSource.getGames().first()) {
                is ApiResponse.Success -> {
                    val games = DataMapper.mapGameResponseToGame(apiResponse.data)
                    emit(Resource.Success(games))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Error("Empty"))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error("Error"))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun getDetailGame(id: Int): Flow<Resource<GameDetail>> = flow {
        emit(Resource.Loading())
        try {
            when (val apiResponse = remoteDataSource.getDetailGame(id).first()) {
                is ApiResponse.Success -> {
                    val gameDetail = DataMapper.mapGameDetailResponseToGameDetail(apiResponse.data)
                    emit(Resource.Success(gameDetail))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Error("Empty"))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error("Error"))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }

    }

    //get all favorite game from database
    override fun getAllGames(): Flow<List<GameDetail>> {
        return localDataSource.getAllGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteGameById(id: Int): Flow<GameDetail> {
        return localDataSource.getFavoriteGameById(id).map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun insertGame(game: GameDetail) {
        val gameEntity = DataMapper.mapGameDetailToEntity(game)
        return localDataSource.insertGame(gameEntity)
    }

    override suspend fun deleteGame(game: GameDetail) {
        val gameEntity = DataMapper.mapGameDetailToEntity(game)
        return localDataSource.deleteGame(gameEntity)
    }

    override fun updateFavoriteGame(game: GameDetail, newState: Boolean) {
        val gameEntity = DataMapper.mapGameDetailToEntity(game)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.updateFavoriteGame(gameEntity, newState)
        }
    }


}