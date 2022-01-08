package com.example.chattingserverspringmvcwebsocket.service

import com.example.chattingserverspringmvcwebsocket.ChatRoom
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.*
import javax.annotation.PostConstruct

@Service
class ChatService(
    val objectMapper: ObjectMapper,
) {
    lateinit var chatRooms: MutableMap<String, ChatRoom>

    @PostConstruct
    private fun init() {
        chatRooms = hashMapOf()
    }

    fun findAllRoom(): List<ChatRoom> = chatRooms.values.toList()

    fun findRoomById(roomId: String): ChatRoom? = chatRooms[roomId]

    fun createRoom(name: String): ChatRoom {
        val randomId = UUID.randomUUID().toString()
        val chatRoom = ChatRoom(roomId = randomId, name = name)
        chatRooms[randomId] = chatRoom
        return chatRoom
    }

    fun <T> sendMessage(session: WebSocketSession, message: T) {
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
    }
}
