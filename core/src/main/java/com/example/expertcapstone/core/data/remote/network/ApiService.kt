package com.example.expertcapstone.core.data.remote.network

import com.example.expertcapstone.core.data.remote.response.GameDetailResponse
import com.example.expertcapstone.core.data.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String
    ): ListGameResponse

    @GET("games/{id}")
    suspend fun getDetailGames(
        @Path("id") id: Int,
        @Query("key") apiKey: String
    ): GameDetailResponse
}
//API TOKEN rawg: b37164468248415987aadcda8a440716 - URL: https://api.rawg.io/api/games?key=b37164468248415987aadcda8a440716
//API TOKEN OMDB: 33e1efba
