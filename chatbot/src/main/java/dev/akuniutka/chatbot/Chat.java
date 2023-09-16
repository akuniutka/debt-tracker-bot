package dev.akuniutka.chatbot;

public class Chat {
    private Long userId;
    private ChatState state;

    public Chat(Long userId, ChatState initialState) {
        if (userId == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (initialState == null) {
            throw new IllegalArgumentException("initial chat state is null");
        }
        this.userId = userId;
        state = initialState;
    }

    public Long getUserId() {
        return userId;
    }

    public ChatState getState() {
        return state;
    }

    public void setState(ChatState state) {
        if (state == null) {
            throw new IllegalArgumentException("chat state is null");
        }
        this.state = state;
    }

    public ChatReply getReplyToMessage(String message) {
        state.processMessage(this, message);
        return new ChatReply(state.getReply(), state.getPossibleAnswers());
    }

    protected Chat() {
    }
}
