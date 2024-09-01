package pro.sky.telegrambot.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.service.NotificationTaskService;

@Service
public class NotificationTaskServiceImpl implements NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskServiceImpl(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Override
    public void save(NotificationTask task) {
        task.setId(null);
        notificationTaskRepository.save(task);
    }

    @Override
    public NotificationTask findNotificationTaskByDate() {
        return notificationTaskRepository.findNotificationTaskByDate();
    }
}
