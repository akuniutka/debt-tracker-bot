package dev.akuniutka.chatbot.simplebot;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatReply;

import java.util.Scanner;

public class SimpleChatBot {
    private static final long userId = 1L;

    public static void main(String[] args) {
        Chat chat = new Chat(userId, new HelloState());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Send message to bot or 'exit' to quit:");
        String message = scanner.nextLine();
        while (!"exit".equals(message)) {
            ChatReply chatReply = chat.getReplyToMessage(message);
            System.out.println("Bot:");
            for (String line : chatReply.getReply()) {
                System.out.println("\t" + line);
            }
            System.out.println("Possible answers:");
            for (String line : chatReply.getPossibleAnswers()) {
                System.out.println("\t" + line);
            }
            System.out.println("Send message to bot or 'exit' to quit:");
            message = scanner.nextLine();
        }
    }
}
