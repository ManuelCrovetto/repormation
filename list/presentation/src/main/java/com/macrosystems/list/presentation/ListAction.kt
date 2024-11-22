package com.macrosystems.list.presentation

import com.macrosystems.core.domain.RepoData

sealed interface ListAction {
    data object LoadRepo: ListAction
    data class NavigateToDetails(val repo: RepoData): ListAction
    data class UserSelected(val userId: Int): ListAction
}