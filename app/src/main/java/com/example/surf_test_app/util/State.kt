package com.example.surf_test_app.util

sealed class State {
    object Error : State()
    object Loading : State()
    object Success : State()
    object WrongRequest : State()
}