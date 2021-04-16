package org.ray.core.data.remote.api.response

sealed class ResponseStatus<out R> {
    data class Success<out T>(val data: T) : ResponseStatus<T>()
    data class Error(val errorMessage: String) : ResponseStatus<Nothing>()
    object Empty : ResponseStatus<Nothing>()
}
