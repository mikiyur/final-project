package com.intentsg.service.user.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
}
