package com.example.vodovozapp.ui

import com.example.vodovozapp.data.model.TovaryList

sealed class Events {

    data class ShowTovary(val tovary: TovaryList) : Events()
    data object LoadingState : Events()
}