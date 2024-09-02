package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.NotificationTask;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {

    @Query(value = "SELECT * FROM notification_task WHERE date = (CURRENT_TIMESTAMP AT TIME ZONE 'UTC' + INTERVAL '3 hours')", nativeQuery = true)
    NotificationTask findNotificationTaskByDate();
}
