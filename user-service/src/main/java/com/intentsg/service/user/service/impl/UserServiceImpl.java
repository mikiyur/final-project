package com.intentsg.service.user.service.impl;

import com.intentsg.model.UserDto;
import com.intentsg.model.exeptions.NoSuchElementExeption;
import com.intentsg.service.user.entity.Item;
import com.intentsg.service.user.entity.User;
import com.intentsg.service.user.repository.ItemRepository;
import com.intentsg.service.user.repository.UserRepository;
import com.intentsg.service.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementExeption("Tour with id: " + id + " not found in data base"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<Long> getToursIdByUserId(Long id) {
        return itemRepository.findAllByUserId(id).stream()
                .map(Item::getTourId)
                .collect(Collectors.toList());
    }
}
