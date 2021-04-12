package com.intentsg.service.user.service;

import com.intentsg.model.ItemDto;
import com.intentsg.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);

    List<Long> getToursIdByUserId(Long id);

    ItemDto addTourToCart(Long userId, Long tourId);

    void deleteTourFromCart(Long userId, Long tourId);

    void cleanCart(Long userId);
}
