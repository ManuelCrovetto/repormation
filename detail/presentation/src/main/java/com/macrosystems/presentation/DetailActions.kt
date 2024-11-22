package com.macrosystems.presentation

sealed interface DetailActions {
    data class FetchLatestEvent(val user: String?, val repoName: String?): DetailActions
}