package com.example.blockchain.newfile.data

sealed class EventSink {
     object GetBalance : EventSink()
     object Connect : EventSink()
     object Disconnect : EventSink()
}