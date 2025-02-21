package com.example.expertcapstone.core.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ListGameResponse(

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("previous")
    val previous: String?,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("results")
    val results: List<GameResponse>
) : Parcelable

