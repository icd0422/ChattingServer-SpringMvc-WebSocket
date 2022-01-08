package com.example.chattingserverspringmvcwebsocket

import com.example.chattingserverspringmvcwebsocket.service.ChatService
import org.springframework.web.socket.WebSocketSession

data class ChatRoom(
    val roomId: String,
    val name: String
) {
    private val sessions: MutableSet<WebSocketSession> = mutableSetOf()

    fun handleActions(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
        if (chatMessage.type == ChatMessage.MessageType.ENTER) {
            sessions.add(session)
            chatMessage.message = chatMessage.sender + "님이 입장했습니다."
        }
        sendMessage(chatMessage, chatService)
    }

    private fun <T> sendMessage(message: T, chatService: ChatService) {
        sessions.parallelStream().forEach { session -> chatService.sendMessage(session, message) }
    }
}
