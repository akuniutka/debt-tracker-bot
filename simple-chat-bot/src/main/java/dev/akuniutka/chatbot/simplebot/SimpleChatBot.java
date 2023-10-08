package dev.akuniutka.chatbot.simplebot;

import dev.akuniutka.chatbot.core.Chat;

import java.util.Scanner;

public class SimpleChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Chat chat = new Chat((long) scanner.hashCode(), new HelloState());
        String message = "";
        while (!"exit".equals(message)) {
            if (!message.isEmpty()) {
                System.out.println("Bot:");
                chat.getReplyToMessage(message).forEach(s -> System.out.println("\t" + s));
            }
            System.out.println("Send message to bot or 'exit' to quit:");
            message = scanner.nextLine();
        }
    }
}
