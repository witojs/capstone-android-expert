package com.example.expertcapstone.core.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameResponse(

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("tba")
    val tba: Boolean,

    @field:SerializedName("dominant_color")
    val dominantColor: String,

    @field:SerializedName("rating_top")
    val ratingTop: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("saturated_color")
    val saturatedColor: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("updated")
    val updated: String,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("released")
    val released: String
) : Parcelable
