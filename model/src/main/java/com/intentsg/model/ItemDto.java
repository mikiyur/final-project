package com.intentsg.model;

import java.time.LocalDateTime;

public class ItemDto {

    private Long id;

    private UserDto user;

    private Long tourId;

    private LocalDateTime registeredAt = LocalDateTime.now();
}
