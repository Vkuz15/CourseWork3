package pro.sky.telegrambot.utils;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class Patterns {

    public static final Pattern PATTERN = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");

    public static final LocalDateTime CURRENT_TIME_TO_MINUTES = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static String BAD_REQUEST_TIME_FORMATTER = "Ошибочный формат даты и времени!\nОтправь корректный формат времени напоминания!";

    public static final String WELCOME_MESSAGE = "Здравствуйте, я Бот Владимира, могу оставить для Вас напоминание \nНапиши напоминание в формате:\nдд.мм.гггг чч:мм и -текст напоминания-";

    public static void sendMessage(TelegramBot telegramBot, Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        telegramBot.execute(message);
    }
}
