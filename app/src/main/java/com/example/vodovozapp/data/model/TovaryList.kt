package com.example.vodovozapp.data.model

import com.google.gson.annotations.SerializedName

data class TovaryList(
    val status: Status,
    @SerializedName("TOVARY") val tovary: List<Tovary>,
)