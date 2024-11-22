package com.macrosystems.presentation

import com.macrosystems.core.domain.RepoEvent
import com.macrosystems.core.presentation.ui.UiText

data class DetailState(
    val isLoading: Boolean = false,
    val repoEvent: RepoEvent? = null,
    val error: UiText? = null
)
