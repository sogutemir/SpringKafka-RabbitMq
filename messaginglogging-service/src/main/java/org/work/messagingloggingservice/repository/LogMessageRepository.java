package org.work.messagingloggingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.work.messagingloggingservice.model.LogMessage;

@Repository
public interface LogMessageRepository extends JpaRepository<LogMessage, Long> {
}
