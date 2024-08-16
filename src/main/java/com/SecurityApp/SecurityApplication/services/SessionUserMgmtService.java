package com.SecurityApp.SecurityApplication.services;

import com.SecurityApp.SecurityApplication.entities.SessionUserMgmtEntity;
import com.SecurityApp.SecurityApplication.entities.User;
import com.SecurityApp.SecurityApplication.repositories.SessionUserMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionUserMgmtService {

    private final SessionUserMgmtRepository sessionUserMgmtRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user, String refreshToken){
        List<SessionUserMgmtEntity> userSessions = sessionUserMgmtRepository.findByUser(user);
        if(userSessions.size() == SESSION_LIMIT){
            userSessions.sort(Comparator.comparing(SessionUserMgmtEntity::getLastUsedAt));

            SessionUserMgmtEntity leastRecentlyUsedSession = userSessions.get(0);
            sessionUserMgmtRepository.delete(leastRecentlyUsedSession);
        }
        SessionUserMgmtEntity newSession = SessionUserMgmtEntity.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        sessionUserMgmtRepository.save(newSession);
    }

    public void validateSession(String refreshToken){
        SessionUserMgmtEntity session = sessionUserMgmtRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session not found " +
                        "for refresh token: " + refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionUserMgmtRepository.save(session);
    }
}
