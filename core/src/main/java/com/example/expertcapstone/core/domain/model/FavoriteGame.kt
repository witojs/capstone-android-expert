package com.example.expertcapstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteGame(
    val id: Int,
    val website: String,
    val rating: Double,
    val description: String,
    val backgroundImage: String,
    val name: String,
    val publishers: String,
    val released: String,
    val isFavorite: Boolean
) : Parcelable
