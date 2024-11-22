package com.macrosystems.list.presentation

import com.macrosystems.core.domain.RepoData

sealed interface ListEvent {
    data class NavigateToDetails(val repoData: RepoData): ListEvent
}