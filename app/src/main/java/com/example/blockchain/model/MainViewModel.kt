package com.example.blockchain.model



import android.util.Log
//import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockchain.api.GnosisSafeApi
import com.example.blockchain.api.RetrofitClient
import com.example.blockchain.model.Transaction
import com.example.blockchain.model.Transfer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _transfers = MutableLiveData<List<Transfer>>()
    val transfers: LiveData<List<Transfer>> get() = _transfers

    private val apiService = RetrofitClient.instance

    // Fetches transfers from the Gnosis Safe API
    suspend fun getTransfers(safeAddress: String) {
        withContext(Dispatchers.IO) {
            try {
                val result = apiService.getTransfers(safeAddress)
                _transfers.postValue(result) // Update LiveData with the fetched transfers
            } catch (e: Exception) {
                Log.e("Error", "Failed to fetch transfers: ${e.message}")
                throw e
            }
        }
    }


    // Creates a new transaction
    fun createTransaction(safeAddress: String, transaction: Transaction) {
        val shouldShowDialog = false

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    apiService.createTransaction(safeAddress, transaction)
                }
            } catch (e: Exception) {
                Log.e("Error", "Failed to create transaction: ${e.message}")
            }
        }
    }
}
