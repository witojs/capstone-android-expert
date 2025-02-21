package com.example.expertcapstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetail(
    val id: Int,
    val website: String,
    val nameOriginal: String,
    val rating: Double,
    val description: String,
    val descriptionRaw: String,
    val backgroundImageAdditional: String,
    val backgroundImage: String,
    val tba: Boolean,
    val ratingTop: Int,
    val name: String,
    val publishers: String,
    val updated: String,
    val slug: String,
    val released: String,
    val isFavorite: Boolean
) : Parcelable

