package com.example.expertcapstone.core.data.remote

import com.example.expertcapstone.core.BuildConfig
import com.example.expertcapstone.core.data.remote.network.ApiResponse
import com.example.expertcapstone.core.data.remote.network.ApiService
import com.example.expertcapstone.core.data.remote.response.GameDetailResponse
import com.example.expertcapstone.core.data.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    private val apiKey = BuildConfig.API_KEY

    suspend fun getGames(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getGames(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(id: Int): Flow<ApiResponse<GameDetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetailGames(id, apiKey)
                if (response.name.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}