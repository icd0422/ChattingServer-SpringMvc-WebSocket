package com.example.chattingserverspringmvcwebsocket

class ChatMessage(
    val type: MessageType,
    val roomId: String,
    val sender: String,
) {
    lateinit var message: String
    enum class MessageType {
        ENTER, TALK
    }
}
