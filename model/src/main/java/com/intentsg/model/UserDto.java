package com.intentsg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String userName;

    private String password;

    private int balance;

    private boolean isAuthenticated;
}
