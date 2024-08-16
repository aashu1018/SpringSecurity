package com.SecurityApp.SecurityApplication.repositories;

import com.SecurityApp.SecurityApplication.entities.SessionUserMgmtEntity;
import com.SecurityApp.SecurityApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionUserMgmtRepository extends JpaRepository<SessionUserMgmtEntity, Long> {
    List<SessionUserMgmtEntity> findByUser(User user);

    Optional<SessionUserMgmtEntity> findByRefreshToken(String refreshToken);
}
