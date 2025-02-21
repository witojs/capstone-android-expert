package com.example.expertcapstone.core.utils

import com.example.expertcapstone.core.data.local.entity.GameEntity
import com.example.expertcapstone.core.data.remote.response.GameDetailResponse
import com.example.expertcapstone.core.data.remote.response.GameResponse
import com.example.expertcapstone.core.domain.model.Game
import com.example.expertcapstone.core.domain.model.GameDetail

object DataMapper {
    fun mapGameResponseToGame(input: List<GameResponse>): List<Game> {
        val gameList = ArrayList<Game>()
        input.map {
            val game = Game(
                rating = it.rating,
                backgroundImage = it.backgroundImage,
                tba = it.tba,
                dominantColor = it.dominantColor,
                ratingTop = it.ratingTop,
                name = it.name,
                saturatedColor = it.saturatedColor,
                id = it.id,
                updated = it.updated,
                slug = it.slug,
                released = it.released
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapGameDetailResponseToGameDetail(input: GameDetailResponse) = GameDetail(
        website = input.website,
        nameOriginal = input.nameOriginal,
        rating = input.rating,
        description = input.description,
        descriptionRaw = input.descriptionRaw,
        backgroundImageAdditional = input.backgroundImageAdditional,
        backgroundImage = input.backgroundImage,
        tba = input.tba,
        ratingTop = input.ratingTop,
        name = input.name,
        publishers = input.publishers[0].name,
        id = input.id,
        updated = input.updated,
        slug = input.slug,
        released = input.released,
        isFavorite = false
    )

    //get detail game data to entity
    fun mapGameDetailToEntity(input: GameDetail) = GameEntity(
        website = input.website,
        rating = input.rating,
        description = input.description,
        backgroundImage = input.backgroundImage,
        name = input.name,
        publishers = input.publishers,
        released = input.released,
        id = input.id,
        updated = input.updated,
        slug = input.slug,
        nameOriginal = input.nameOriginal,
        backgroundImageAdditional = input.backgroundImageAdditional,
        descriptionRaw = input.descriptionRaw,
        tba = input.tba,
        ratingTop = input.ratingTop,
    )

    //show entity data to domain(favorite game)
    fun mapEntitiesToDomain(input: List<GameEntity>): List<GameDetail> =
        input.map {
            GameDetail(
                id = it.id,
                website = it.website,
                nameOriginal = it.nameOriginal,
                rating = it.rating,
                description = it.description,
                descriptionRaw = it.descriptionRaw,
                backgroundImageAdditional = it.backgroundImageAdditional,
                backgroundImage = it.backgroundImage,
                tba = it.tba,
                ratingTop = it.ratingTop,
                name = it.name,
                publishers = it.publishers,
                updated = it.updated,
                slug = it.slug,
                released = it.released,
                isFavorite = false
            )
        }

    fun mapEntityToDomain(input: GameEntity?): GameDetail {
        return if (input != null) {
            GameDetail(
                id = input.id,
                website = input.website,
                nameOriginal = input.nameOriginal,
                rating = input.rating,
                description = input.description,
                descriptionRaw = input.descriptionRaw,
                backgroundImageAdditional = input.backgroundImageAdditional,
                backgroundImage = input.backgroundImage,
                tba = input.tba,
                ratingTop = input.ratingTop,
                name = input.name,
                publishers = input.publishers,
                updated = input.updated,
                slug = input.slug,
                released = input.released,
                isFavorite = input.isFavorite
            )
        } else {
            GameDetail(
                id = 0,
                website = "",
                nameOriginal = "",
                rating = 0.0,
                description = "",
                descriptionRaw = "",
                backgroundImageAdditional = "",
                backgroundImage = "",
                tba = false,
                ratingTop = 0,
                name = "",
                publishers = "",
                updated = "",
                slug = "",
                released = "",
                isFavorite = false
            )
        }
    }
}