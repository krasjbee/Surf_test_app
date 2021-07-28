package com.example.surf_test_app.util

sealed class Resource<T>(val data: T?, val message: String?) {
    class RequestError<T>(message: String) : Resource<T>(data = null, message)
    class Success<T>(data: T) : Resource<T>(data, message = null)
    class UnexpectedError<T>(message: String?) : Resource<T>(data = null, message)
}