package com.intentsg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private Long id;

    private String userName;

    private String password;

    private int balance;

    private boolean isAuthenticated;

    private List<UserItemDto> items;
}
