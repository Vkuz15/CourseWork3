package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.service.NotificationTaskService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;

import static pro.sky.telegrambot.utils.Patterns.*;


@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final NotificationTaskService notificationTaskService;

    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(NotificationTaskService notificationTaskService, TelegramBot telegramBot) {
        this.notificationTaskService = notificationTaskService;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            logger.info("Processing update: {}", update);

            if (message != null) {
                if ("/start".equals(message.text())) {
                    sendMessage(telegramBot, message.chat().id(), WELCOME_MESSAGE);
                } else {
                    try {
                        addTask(message.chat().id(), message.text());
                    } catch (Exception exception) {
                        logger.error("Error adding task: {}", exception.getMessage());
                        sendMessage(telegramBot, message.chat().id(), BAD_REQUEST_TIME_FORMATTER);
                    }
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void addTask(Long chatId, String task) throws DateTimeParseException {
        Matcher matcher = PATTERN.matcher(task);

        if (matcher.matches()) {
            String dateTimeStr = matcher.group(1);
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, TIME_FORMATTER);
            String message = matcher.group(3);
            notificationTaskService.save(new NotificationTask(chatId, message, dateTime));
            sendMessage(telegramBot, chatId, "Напоминание: " + message + ", добавлено!");
        }
    }
}

