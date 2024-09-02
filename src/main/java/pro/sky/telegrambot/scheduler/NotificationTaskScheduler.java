package pro.sky.telegrambot.scheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.service.NotificationTaskService;

@Component
public class NotificationTaskScheduler {

    private final NotificationTaskService notificationTaskService;
    private final TelegramBot telegramBot;

    public NotificationTaskScheduler(NotificationTaskService notificationTaskService, TelegramBot telegramBot) {
        this.notificationTaskService = notificationTaskService;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        NotificationTask task = notificationTaskService.findNotificationTaskByDate();
        if (task != null) {
            SendMessage message = new SendMessage(task.getChatId(), "Ваше напоминание:\n" + task.getMessage());
            telegramBot.execute(message);
        }
    }
}
