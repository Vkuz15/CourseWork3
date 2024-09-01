package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.NotificationTask;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {

    @Query(value = "SELECT * FROM notification_task WHERE to_char(date, 'YYYY-MM-DD HH24:MI') to_char(CURRENT_TIMESTAMP AT TIME ZONE INTERVAL '+03:00', 'YYYY-MM-DD HH24:MI')", nativeQuery = true)
    NotificationTask findNotificationTaskByDate();
}
