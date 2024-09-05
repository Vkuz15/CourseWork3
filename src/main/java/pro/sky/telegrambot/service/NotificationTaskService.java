package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.NotificationTask;

import java.util.List;

public interface NotificationTaskService {

    void save(NotificationTask task);

    List<NotificationTask> findNotificationTasksByDate();
}
