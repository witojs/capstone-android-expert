package com.example.expertcapstone.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "website")
    val website: String,
    @ColumnInfo(name = "name_original")
    val nameOriginal: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "description_raw")
    val descriptionRaw: String,
    @ColumnInfo(name = "background_image_additional")
    val backgroundImageAdditional: String,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String,
    @ColumnInfo(name = "tba")
    val tba: Boolean,
    @ColumnInfo(name = "rating_top")
    val ratingTop: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "publishers")
    val publishers: String,
    @ColumnInfo(name = "updated")
    val updated: String,
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)


