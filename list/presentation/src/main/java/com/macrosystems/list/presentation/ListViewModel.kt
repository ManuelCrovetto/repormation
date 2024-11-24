package com.macrosystems.list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macrosystems.core.domain.Result
import com.macrosystems.core.presentation.ui.asUiText
import com.macrosystems.list.domain.UserGithubRepository
import com.macrosystems.list.domain.User
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val userGithubRepo: UserGithubRepository
): ViewModel() {

    var state by mutableStateOf(
        ListState(
            users = listOf(
                User(
                    id = 0,
                    name = "JakeWharton",
                    avatar = "https://avatars.githubusercontent.com/u/66577?v=4"
                ),
                User(
                    id = 1,
                    name = "Infinum",
                    avatar = "https://avatars.githubusercontent.com/u/97652?v=4"
                )
            ),
            selectedUserId = 0
        )
    )
        private set

    private val eventChannel = Channel<ListEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: ListAction) {
        when(action) {
            ListAction.LoadRepo -> {
                fetchRepoList()
            }

            is ListAction.UserSelected -> {
                state = state.copy(selectedUserId = action.userId)
                fetchRepoList()
            }

            is ListAction.NavigateToDetails -> {
                viewModelScope.launch {
                    eventChannel.send(ListEvent.NavigateToDetails(action.repo))
                }
            }
        }
    }


    private fun fetchRepoList() {
        viewModelScope.launch {
            val selectedUser = state.users.firstOrNull { it.id == state.selectedUserId } ?: return@launch
            state = state.copy(isLoading = true)
            when (val result = userGithubRepo.fetchRepositories(selectedUser.name)) {
                is Result.Error -> {
                    val errorMessage = result.error.asUiText()
                    state = state.copy(error = errorMessage)
                }
                is Result.Success -> {
                    val sortedList = result.data.sortedByDescending { it.stars }
                    state = state.copy(repoList = sortedList, isLoading = false)
                }
            }
        }
    }

}