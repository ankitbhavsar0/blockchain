package com.example.blockchain.model

data class Transfer(
    val id: String,
    val amount: String,  // Amount transferred
    val sender: String   // Address of the sender
)