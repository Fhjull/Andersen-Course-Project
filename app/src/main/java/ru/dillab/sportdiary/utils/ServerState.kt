package ru.dillab.sportdiary.utils


sealed class ServerState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : ServerState<T>(data)
    class Success<T>(data: T?) : ServerState<T>(data)
    class Error<T>(message: String, data: T? = null) : ServerState<T>(data, message)
}