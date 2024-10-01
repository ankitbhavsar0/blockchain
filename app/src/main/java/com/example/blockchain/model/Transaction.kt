package com.example.blockchain.model

data class Transaction(
    val to: String,    // Recipient address
    val value: String, // Amount to send in Wei
    val data: String   // Additional data for smart contracts (optional, usually empty)
)