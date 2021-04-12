package com.intentsg.service.user.service;

import com.intentsg.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    List<Long> getToursIdByUserId(Long id);
}
