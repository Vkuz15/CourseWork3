package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.NotificationTask;

public interface NotificationTaskService {

    void save(NotificationTask task);

    NotificationTask findNotificationTaskByDate();
}
