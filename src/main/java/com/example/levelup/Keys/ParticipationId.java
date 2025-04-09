package com.example.levelup.Keys;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Embeddable
@Data
public class ParticipationId implements Serializable {
    private int challengeId;
    private int userId;

    public ParticipationId() {
    }

    public ParticipationId(int challengeId, int userId) {
        this.challengeId = challengeId;
        this.userId = userId;
    }
}
