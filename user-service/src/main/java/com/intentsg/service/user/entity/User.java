package com.intentsg.service.user.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    @Size(min=1, max=50)
    @Pattern(regexp = "[A-Za-zЄ-ЯҐа-їґ -]*")
    @Column(unique = true)
    private String userName;

    @NotEmpty
    @Size(min=6, max=100)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private int balance;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String secretKey;

    @OneToMany (cascade = CascadeType.REMOVE, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Item> items = new ArrayList<>();
}
