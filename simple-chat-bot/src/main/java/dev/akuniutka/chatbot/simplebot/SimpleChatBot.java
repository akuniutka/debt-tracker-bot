package dev.akuniutka.chatbot.simplebot;

import dev.akuniutka.chatbot.core.Chat;

import java.util.List;
import java.util.Scanner;

public class SimpleChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Chat chat = new Chat((long) scanner.hashCode(), new HelloState());
        System.out.println("Send message to bot or 'exit' to quit:");
        String message = scanner.nextLine();
        while (!"exit".equals(message)) {
            List<String> reply = chat.getReplyToMessage(message);
            System.out.println("Bot:");
            for (String line : reply) {
                System.out.println("\t" + line);
            }
            System.out.println("Send message to bot or 'exit' to quit:");
            message = scanner.nextLine();
        }
    }
}
