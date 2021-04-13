package com.intentsg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {

    private Long id;

    private UserDto user;

    private Long tourId;

    private LocalDateTime registeredAt;
}
