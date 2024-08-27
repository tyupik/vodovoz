package com.example.vodovozapp.data.model

import com.google.gson.annotations.SerializedName

data class Tovary(
    @SerializedName("ID") val id: Int,
    @SerializedName("NAME") val name: String,
    val data: List<Tovar>,
)