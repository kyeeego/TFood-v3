package com.kyeeego.TFood.application.repository;

import com.kyeeego.TFood.domain.models.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SessionRepository extends MongoRepository<Session, String> {
    List<Session> findByUserEmail(String userEmail);
    void deleteByUserEmail(String userEmail);
    void deleteByFingerprint(String fingerprint);
    Optional<Session> findByRefreshToken(String refreshToken);
}
