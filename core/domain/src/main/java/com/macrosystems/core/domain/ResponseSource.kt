package com.macrosystems.core.domain

import com.macrosystems.core.domain.util.DataError

sealed class ResponseSource <out T> {
    data class Error <T> (val error: DataError.Network): ResponseSource<T>()
    data class Success <T> (val data: T, val dataOptional: Any? = null): ResponseSource<T>()
}