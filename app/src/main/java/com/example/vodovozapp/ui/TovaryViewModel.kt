package com.example.vodovozapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vodovozapp.data.TovaryRepository
import com.example.vodovozapp.data.model.TovaryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TovaryViewModel() : ViewModel() {

    private val tovaryRepository = TovaryRepository()

    private lateinit var itemsList: TovaryList
    private val _events: MutableStateFlow<Events> = MutableStateFlow(Events.LoadingState)
    val events: StateFlow<Events>
        get() = _events

    private var selectedChipIndex = 0

    init {
        getTovary()
    }

    fun onChipClick(index: Int) {
        selectedChipIndex = index
        updateUi()
    }

    private fun updateUi() {
        viewModelScope.launch {
            _events.tryEmit(Events.ShowTovary(itemsList, selectedChipIndex))
        }
    }

    private fun getTovary() =
        viewModelScope.launch {
            itemsList = tovaryRepository.getTovary()
            updateUi()
        }
}