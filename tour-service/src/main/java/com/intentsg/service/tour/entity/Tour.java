package com.intentsg.service.tour.entity;


import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tours")
public class Tour {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The title cannot be empty")
    @Size(min=1, max=200)
    private String title;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private Integer price;

    private String imageUrl;
}
