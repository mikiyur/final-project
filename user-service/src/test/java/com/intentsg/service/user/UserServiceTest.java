package com.intentsg.service.user;

import com.intentsg.model.ItemDto;
import com.intentsg.model.UserDto;
import com.intentsg.model.UserProfile;
import com.intentsg.model.exeptions.AccessDeniedException;
import com.intentsg.model.exeptions.AlreadyExistsExeption;
import com.intentsg.model.exeptions.NoSuchElementExeption;
import com.intentsg.service.user.entity.Item;
import com.intentsg.service.user.entity.User;
import com.intentsg.service.user.repository.ItemRepository;
import com.intentsg.service.user.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CurrentUsers currentUsers;

    @Mock
    private PasswordEncoder encoder;

    private static final long USER_1_ID = 1L;
    private static final long USER_20_ID = 20L;
    private static final long ITEM_5_ID = 5L;
    private static final long ITEM_50_ID = 50L;
    private static final String WALID_USERNAME = "Username";
    private static final String WALID_PASSWORD = "Password";
    private static final String NOT_WALID_USERNAME = "User1";
    private static final String NOT_WALID_PASSWORD = "111";



    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;
    private UserProfile walidUserProfile;
    private UserProfile notWalidUserProfile;
    private Item item;
    private Item newItem;
    private ItemDto itemDto;
    private List<Item> longList;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        user1 = new User();
        user1.setId(USER_1_ID);
        user1.setUserName(WALID_USERNAME);
        user1.setPassword(WALID_PASSWORD);
        user1.setSecretKey("sk1");
        user1.setItems(new ArrayList<>());
        user1.setBalance(1000);

        user2 = new User();
        user2.setId(USER_20_ID);
        user2.setUserName("User2");
        user2.setPassword("pass2");
        user2.setSecretKey("sk2");
        user2.setItems(new ArrayList<>());
        user2.setBalance(2000);

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

        walidUserProfile = new UserProfile();
        walidUserProfile.setUserName(WALID_USERNAME);
        walidUserProfile.setPassword(WALID_PASSWORD);
        item = new Item();
        item.setId(ITEM_5_ID);
        item.setTourId(ITEM_5_ID);
        item.setPrice(345);
        user1.getItems().add(item);

        newItem = new Item();

        itemDto = new ItemDto();
        itemDto.setId(item.getId());

        longList = new ArrayList<>();
        longList.add(new Item());

        when(userRepository.findById(USER_1_ID)).thenReturn(Optional.of(user1));
        when(userRepository.findById(USER_20_ID)).thenReturn(Optional.of(user2));
        when(userRepository.findById(99L)).thenThrow(new NoSuchElementExeption());
        when(modelMapper.map(user1,UserDto.class)).thenReturn(userDto1);
        when(modelMapper.map(user2,UserDto.class)).thenReturn(userDto2);
        when(modelMapper.map(item,ItemDto.class)).thenReturn(itemDto);
        when(itemRepository.findAllByUserId(USER_1_ID)).thenReturn(longList);
        when(itemRepository.save(newItem)).thenReturn(item);
        when(currentUsers.checkExists(user1)).thenReturn(true);
        when(encoder.encode(WALID_PASSWORD)).thenReturn(WALID_PASSWORD);
        when(modelMapper.map(walidUserProfile, User.class)).thenReturn(user1);
    }

    @Test
    public void getUserByIdTest(){
        UserDto actualUser1 = userService.getUserById(USER_1_ID);
        UserDto actualUser2 = userService.getUserById(USER_20_ID);
        assertThat(actualUser1.getId().equals(user1.getId()));
        assertThat(actualUser2.getId().equals(user2.getId()));
    }

    @Test
    public void getUserByIdExceptionTest(){
        assertThatExceptionOfType(NoSuchElementExeption.class).isThrownBy(() -> {
            userService.getUserById(99L);
        });
    }

    @Test
    public void getToursIdByUserIdTest(){
        List<Long> actualList = userService.getToursIdByUserId(USER_1_ID);
        assertThat(actualList.size()==1);
    }

    @Test
    public void getToursIdByUserIdExceptionTest(){
        assertThatExceptionOfType(AccessDeniedException.class).isThrownBy(() -> {
            userService.getToursIdByUserId(USER_20_ID);
        });
    }

    @Test
    public void addTourToCartTest(){
        ItemDto actualItemDto = userService.addTourToCart(USER_1_ID,ITEM_50_ID,100);
        assertThat(actualItemDto.getId().equals(itemDto.getId()));
    }

    @Test
    public void addTourToCartExceptionTest(){
        assertThatExceptionOfType(AlreadyExistsExeption.class).isThrownBy(() -> {
            userService.addTourToCart(USER_1_ID,ITEM_5_ID,100);
        });
    }

    @Test
    public void deleteTourFromCartTest(){
        assertThat(userService.deleteTourFromCart(USER_1_ID, 5L));
    }

    @Test
    public void cleanCartTest(){
        assertThat(userService.cleanCart(USER_1_ID));
    }

    @Test
    public void buyToursTest(){
        assertThat(userService.buyTours(USER_1_ID));
    }

//    @Test
//    public void signUpTest(){
//        UserDto actualUserDto = userService.signUp(walidUserProfile);
//        System.out.println(actualUserDto);
//        assertThat(actualUserDto.getId().equals(userDto1.getId()));
//    }

}
