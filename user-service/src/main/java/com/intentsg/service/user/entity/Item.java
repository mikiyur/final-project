package com.intentsg.service.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    private Long tourId;

    private LocalDateTime registeredAt = LocalDateTime.now();
}
