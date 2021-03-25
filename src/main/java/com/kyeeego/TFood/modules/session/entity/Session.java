package com.kyeeego.TFood.modules.session.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sessions")
@NoArgsConstructor
public class Session {

    @Id
    private String id;

    private String refreshToken;

    private String userEmail;

    private Long expiresAt;

    private String fingerprint;

    public Session(String userEmail, Long expiresAt, String fingerprint) {
        this.userEmail = userEmail;
        this.expiresAt = expiresAt;
        this.fingerprint = fingerprint;
    }
}
