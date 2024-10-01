package com.example.blockchain.newfile

import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

class Web3Provider {
    private val web3j: Web3j = Web3j.build(HttpService("https://sepolia.infura.io/v3/fb816cdd6c064f049f7a3c023824d662"))

    fun getWeb3j(): Web3j {
        return web3j
    }
}