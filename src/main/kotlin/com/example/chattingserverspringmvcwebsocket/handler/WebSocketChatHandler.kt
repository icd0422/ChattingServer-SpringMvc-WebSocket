package com.example.chattingserverspringmvcwebsocket.handler

import com.example.chattingserverspringmvcwebsocket.ChatMessage
import com.example.chattingserverspringmvcwebsocket.service.ChatService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


@Component
class WebSocketChatHandler(
    val objectMapper: ObjectMapper,
    val chatService: ChatService
) : TextWebSocketHandler() {

    private val logger: Logger = LoggerFactory.getLogger(WebSocketChatHandler::class.java)

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        logger.info("payload {}", payload)
//        val textMessage: TextMessage = TextMessage("Welcome chatting server~^^")
//        session.sendMessage(textMessage)
        val chatMessage = objectMapper.readValue(payload, ChatMessage::class.java)
        val room = chatService.findRoomById(chatMessage.roomId)
        room!!.handleActions(session, chatMessage, chatService)
    }
}
