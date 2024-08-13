package com.SecurityApp.SecurityApplication.services;

import com.SecurityApp.SecurityApplication.entities.SessionEntity;
import com.SecurityApp.SecurityApplication.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public void createNewSession(Long userId, String token){
        sessionRepository.deleteById(userId);
        SessionEntity session = new SessionEntity();
        session.setSessionId(userId);
        session.setToken(token);
        sessionRepository.save(session);
    }

    public boolean isTokenValid(Long userId, String token){
        Optional<SessionEntity> session = sessionRepository.findById(userId);
        return session.isPresent() && session.get().getToken().equals(token);
    }
}
