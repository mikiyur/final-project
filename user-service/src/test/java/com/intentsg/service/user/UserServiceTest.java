package com.intentsg.service.user;

import com.intentsg.model.UserDto;
import com.intentsg.service.user.entity.User;
import com.intentsg.service.user.repository.ItemRepository;
import com.intentsg.service.user.repository.UserRepository;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.intentsg.service.user.service.impl.UserServiceImpl;
import com.intentsg.service.user.tools.CurrentUsers;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import org.modelmapper.ModelMapper;


import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ItemRepository itemRepository;

    @Mock
    CurrentUsers currentUsers;

    @Mock
    PasswordEncoder encoder;

    private static final long USER_1_ID = 1L;

    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        user1 = User.builder()
                .id(1L)
                .userName("User1")
                .password("pass1")
                .secretKey("sk1")
                .items(new ArrayList<>())
                .balance(1000)
                .build();
        user2 = User.builder()
                .id(20L)
                .userName("User2")
                .password("pass2")
                .secretKey("sk2")
                .items(new ArrayList<>())
                .balance(2000)
                .build();
        userDto1 = UserDto.builder()
                .id(user1.getId())
                .userName(user1.getUserName())
                .secretKey(user1.getSecretKey())
                .items(new ArrayList<>())
                .balance(user1.getBalance())
                .build();
        userDto2 = UserDto.builder()
                .id(user2.getId())
                .userName(user2.getUserName())
                .secretKey(user2.getSecretKey())
                .items(new ArrayList<>())
                .balance(user2.getBalance())
                .build();

        when(userRepository.findById(USER_1_ID)).thenReturn(Optional.of(user1));


        when(modelMapper.map(user1,UserDto.class)).thenReturn(userDto1);
    }

    @Test
    public void getUserByIdTest(){
        UserDto actualUser1 = userService.getUserById(1L);
        System.out.println(actualUser1);
        assertThat(actualUser1.getId().equals(user1.getId()));
    }
}
