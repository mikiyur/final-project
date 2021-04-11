package com.intentsg.model;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TourDto {

    private Long id;

    private String title;

    private String description;

    private int price;
}
