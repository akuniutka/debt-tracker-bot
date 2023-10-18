package io.github.akuniutka.debttrackerbot.script;

import io.github.akuniutka.debttrackerbot.entity.Chat;
import io.github.akuniutka.debttrackerbot.service.EntryService;

import java.util.stream.Stream;

public enum ChatState {
    WAITING_FOR_COMMAND(new WaitingForCommandState()),
    WAITING_FOR_AMOUNT(new WaitingForAmountState()),
    WAITING_FOR_NAME(new WaitingForNameState());

    private final AbstractState state;

    ChatState(AbstractState state) {
        this.state = state;
    }

    public static void setEntryService(EntryService entryService) {
        Stream.of(ChatState.values())
                .forEach(a -> a.state.setEntryService(entryService));
    }

    public void addUserMessage(Chat chat, String userMessage) {
        state.addUserMessage(chat, userMessage);
    }
}
