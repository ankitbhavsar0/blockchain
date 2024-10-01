package com.example.blockchain.newfile.data

sealed class UiEvent {
    data class Message(val error: String) : UiEvent()
}