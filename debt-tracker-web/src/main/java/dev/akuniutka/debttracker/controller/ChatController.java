package dev.akuniutka.debttracker.controller;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.debttracker.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;

    // TODO: return 404 if no currency found
    @GetMapping("/{id}")
    @Operation(summary = "retrieve chat by id")
    public Chat getChat(@PathVariable Long id) {
        return chatService.getChat(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat " + id + " not found")
        );
    }
}
