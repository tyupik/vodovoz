package com.example.vodovozapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TovaryView(tovaryViewModel: TovaryViewModel) {

    val state: Events by tovaryViewModel.events.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {

        when (val event = state) {
            is Events.ShowTovary -> {
                TovaryListItems(
                    tovaryList = event.tovary,
                    selectedChipIndex = event.selectedChipIndex,
                    onChipClick = tovaryViewModel::onChipClick
                )
            }

            is Events.LoadingState -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.inversePrimary,
                )
            }
        }
    }
}