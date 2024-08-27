package com.example.vodovozapp.data.model

import com.google.gson.annotations.SerializedName

data class Tovar(
    @SerializedName("DETAIL_PICTURE") val imageUrl: String,
    @SerializedName("EXTENDED_PRICE") val extendedPrice: List<Price>,
)