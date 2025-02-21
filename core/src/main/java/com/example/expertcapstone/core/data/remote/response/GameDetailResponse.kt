package com.example.expertcapstone.core.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameDetailResponse(

	@field:SerializedName("website")
	val website: String,

	@field:SerializedName("name_original")
	val nameOriginal: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("description_raw")
	val descriptionRaw: String,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("publishers")
	val publishers: List<PublishersItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updated")
	val updated: String,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("released")
	val released: String
) : Parcelable

@Parcelize
data class PublishersItem(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class EsrbRating(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class GenresItem(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable
