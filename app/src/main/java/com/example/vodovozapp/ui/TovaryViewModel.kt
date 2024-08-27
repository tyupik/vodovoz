package com.example.vodovozapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vodovozapp.data.TovaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TovaryViewModel() : ViewModel() {

    private val tovaryRepository = TovaryRepository()

    private val _tovary: MutableStateFlow<Events> = MutableStateFlow(Events.LoadingState)
    val tovary: StateFlow<Events>
        get() = _tovary

    init {
        getTovary()
    }

    private fun getTovary() =
        viewModelScope.launch {
            val tovary = tovaryRepository.getTovary()
            _tovary.tryEmit(Events.ShowTovary(tovary))
        }
}