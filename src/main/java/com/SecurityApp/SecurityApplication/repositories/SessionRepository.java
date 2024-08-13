package com.SecurityApp.SecurityApplication.repositories;

import com.SecurityApp.SecurityApplication.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
}
