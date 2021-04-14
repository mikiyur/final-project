package com.intentsg.service.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "items")
@Data
@EqualsAndHashCode
public class Item {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private User user;

    @EqualsAndHashCode.Exclude
    private Long tourId;

    @EqualsAndHashCode.Exclude
    private Integer price;

    @EqualsAndHashCode.Exclude
    private LocalDateTime registeredAt = LocalDateTime.now();
}
