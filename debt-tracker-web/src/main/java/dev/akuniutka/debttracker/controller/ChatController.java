package dev.akuniutka.debttracker.controller;

import dev.akuniutka.chatbot.core.Chat;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    // TODO: return 404 if no currency found
    @GetMapping("/{id}")
    @Operation(summary = "retrieve chat by id")
    public Chat getChat(@PathVariable Long id) {
        return null;
    }
}
