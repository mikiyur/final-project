package com.intentsg.service.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @Pattern(regexp = "[A-Za-z -]*")
    private String userName;

    @NotEmpty
    @Size(min=6, max=32)
    private String password;

    private int balance;

    private boolean isAuthenticated;

    @OneToMany (cascade = CascadeType.REMOVE, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Item> items = new ArrayList<>();
}
