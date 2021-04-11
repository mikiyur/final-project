package com.intentsg.service.tour.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "tours")
@Data
public class Tour {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    @Size(min=1, max=100)
    @Pattern(regexp = "[A-Za-z0-9 -]*")
    private String title;

    @Lob
    private String description;

    private int price;
}
