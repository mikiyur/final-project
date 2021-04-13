package com.intentsg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserItemDto {

    private Long tourId;

    private LocalDateTime registeredAt;
}
