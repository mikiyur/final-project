package com.intentsg.service.user.service.impl;

import com.intentsg.model.UserDto;
import com.intentsg.model.exeptions.NoSuchElementExeption;
import com.intentsg.service.user.entity.User;
import com.intentsg.service.user.repository.UserRepository;
import com.intentsg.service.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementExeption("Tour with id: " + id + " not found in data base"));
        return modelMapper.map(user, UserDto.class);
    }


}
