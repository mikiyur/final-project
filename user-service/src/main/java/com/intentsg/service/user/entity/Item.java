package com.intentsg.service.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    private Integer price;

    private LocalDateTime registeredAt = LocalDateTime.now();
}
