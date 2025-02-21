package com.example.expertcapstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val rating: Double,
    val backgroundImage: String,
    val tba: Boolean,
    val dominantColor: String,
    val ratingTop: Int,
    val name: String,
    val saturatedColor: String,
    val id: Int,
    val updated: String,
    val slug: String,
    val released: String
) : Parcelable
