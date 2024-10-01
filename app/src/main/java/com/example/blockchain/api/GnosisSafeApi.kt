package com.example.blockchain.api


import com.example.blockchain.model.Transaction
import com.example.blockchain.model.Transfer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GnosisSafeApi {

    // Fetches the list of transfers associated with the Safe
    @GET("safes/{safeAddress}/multisig/transfers/")
    suspend fun getTransfers(@Path("safeAddress") safeAddress: String): List<Transfer>

    // Creates a new transaction in the Safe
    @POST("safes/{safeAddress}/multisig/transactions/")
    suspend fun createTransaction(
        @Path("safeAddress") safeAddress: String,
        @Body transaction: Transaction
    ): Response<Void>
}