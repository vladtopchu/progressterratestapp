package ru.topchu.progressterra.utils

data class ViewState<T> (
    val response: T? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)