package com.example.levelup.models;

import com.example.levelup.Enums.ParticipationStatus;
import com.example.levelup.Keys.ParticipationId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "participates")
public class Participation {

    @EmbeddedId
    private ParticipationId id;

    @ManyToOne
    @MapsId("challengeId")
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="invited_by")
    private int invitedBy;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ParticipationStatus status;

    @Column(name = "progress")
    private int progress;


}
