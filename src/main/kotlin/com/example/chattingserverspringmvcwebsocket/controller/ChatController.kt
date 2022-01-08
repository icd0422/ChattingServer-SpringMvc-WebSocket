package com.example.chattingserverspringmvcwebsocket.controller

import com.example.chattingserverspringmvcwebsocket.service.ChatService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(
    val chatService: ChatService
) {

    @PostMapping
    fun createRoom(@RequestParam name: String) = chatService.createRoom(name)

    @GetMapping
    fun findAllRoom() = chatService.findAllRoom()
}
