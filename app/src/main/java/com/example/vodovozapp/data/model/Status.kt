package com.example.vodovozapp.data.model

import com.google.gson.annotations.SerializedName

enum class Status {
    @SerializedName("Success") SUCCESS,
    @SerializedName("Error") ERROR,
}