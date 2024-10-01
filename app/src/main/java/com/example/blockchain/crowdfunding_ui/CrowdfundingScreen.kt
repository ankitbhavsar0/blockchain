package com.example.blockchain.crowdfunding_ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.blockchain.api.RetrofitClient
import com.example.blockchain.model.MainViewModel
import com.example.blockchain.model.Transaction
import com.example.blockchain.model.Transfer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrowdfundingScreen(viewModel: MainViewModel) {
    var safeAddress by remember { mutableStateOf("0xa17e1728BE16939E44bF7Bd7b348a19222e03dEd") }
    var recipient by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var transfers by remember { mutableStateOf<List<Transfer>>(emptyList()) }

    LaunchedEffect(safeAddress) {
        // Fetch the transfers manually when the composable is composed
        try {
            viewModel.getTransfers(safeAddress)
            transfers = viewModel.transfers.value ?: emptyList() // Manually update transfers
        } catch (e: Exception) {
            Log.e("Error", "Failed to fetch transfers: ${e.message}")
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Transfers:")

        transfers.forEach { transfer ->
            Text("${transfer.sender} sent ${transfer.amount} wei")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = recipient, onValueChange = { recipient = it }, label = { Text("Recipient Address") })
        TextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount (in Wei)") })

        Button(onClick = {
            val transaction = Transaction(to = recipient, value = amount, data = "")
            viewModel.createTransaction(safeAddress, transaction)
        }) {
            Text("Send Transaction")
        }
    }
}