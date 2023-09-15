package dev.akuniutka.debttracker.script;

class WaitingForCorrectAmountChatState extends WaitingForAmountChatState{
    private static final long serialVersionUID = -7952235218973629245L;
    private static final String INCORRECT_AMOUNT_MESSAGE = "The amount is incorrect.";

    WaitingForCorrectAmountChatState() {
        reply.add(0, INCORRECT_AMOUNT_MESSAGE);
    }
}
