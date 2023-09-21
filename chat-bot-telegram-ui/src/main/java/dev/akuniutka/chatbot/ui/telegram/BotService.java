package dev.akuniutka.chatbot.ui.telegram;

import dev.akuniutka.chatbot.core.Chat;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class BotService extends TelegramLongPollingBot {
    private final ChatService chatService;
    private final String botUsername;

    public BotService(String botUsername, String botToken, ChatService chatService) {
        super(botToken);
        if (botUsername == null) {
            throw new IllegalArgumentException("bot username is null");
        }
        if (chatService == null) {
            throw new IllegalArgumentException("chat service is null");
        }
        this.botUsername = botUsername;
        this.chatService = chatService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long userId = message.getChatId();
        Chat chat = chatService.getChat(userId);
        List<String> reply = chat.getReplyToMessage(message.getText());
        if (reply != null && !reply.isEmpty()) {
            for (String line : reply) {
                SendMessage response = new SendMessage();
                response.setChatId(String.valueOf(userId));
                response.setText(line);
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    System.out.println("Error while sending message to Telegram");
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
