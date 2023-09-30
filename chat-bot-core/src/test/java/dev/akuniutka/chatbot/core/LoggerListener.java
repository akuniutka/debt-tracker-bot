package dev.akuniutka.chatbot.core;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

public class LoggerListener extends ListAppender<ILoggingEvent> {
    private Logger logger;
    private Level storedLevel;

    public void attach(Class<?> clazz) {
        list.clear();
        start();
        logger = (Logger) LoggerFactory.getLogger(clazz);
        logger.addAppender(this);
    }

    public void detach() {
        logger.detachAppender(this);
        stop();
    }

    public int getSize() {
        return list.size();
    }

    public String getMessage(int i) {
        return list.get(i).getFormattedMessage();
    }

    public Level getLevel(int i) {
        return list.get(i).getLevel();
    }

    public void changeLevel(Level level) {
        if (storedLevel == null) {
            storedLevel = logger.getLevel();
        }
        logger.setLevel(level);
    }

    public void restoreLevel() {
        logger.setLevel(storedLevel);
        storedLevel = null;
    }
}
