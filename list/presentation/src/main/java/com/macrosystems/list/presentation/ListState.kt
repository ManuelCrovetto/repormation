package com.macrosystems.list.presentation

import com.macrosystems.core.domain.RepoData
import com.macrosystems.core.presentation.ui.UiText
import com.macrosystems.list.domain.User

data class ListState(
    val isLoading: Boolean = false,
    val users: List<User> = listOf(),
    val selectedUserId: Int,
    val repoList: List<RepoData> = listOf(),
    val error: UiText? = null
)
