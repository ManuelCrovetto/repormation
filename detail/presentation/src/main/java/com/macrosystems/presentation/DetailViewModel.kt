package com.macrosystems.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macrosystems.core.domain.Result
import com.macrosystems.core.presentation.ui.asUiText
import com.macrosystems.detail.domain.EventsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val eventsRepo: EventsRepository
): ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    private val eventChannel = Channel<DetailEvents>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: DetailActions) {
        when(action) {
            is DetailActions.FetchLatestEvent -> fetchLatestEvent(user = action.user, repoName = action.repoName)
        }
    }

    private fun fetchLatestEvent(user: String?, repoName: String?) {
        user ?: return
        repoName ?: return
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = eventsRepo.fetchLatestEvent(user, repoName)) {
                is Result.Error -> {
                    val errorMessage = result.error.asUiText()
                    state = state.copy(error = errorMessage)
                }
                is Result.Success -> {

                    state = state.copy(isLoading = false, repoEvent = result.data.firstOrNull())
                }
            }
        }
    }

}