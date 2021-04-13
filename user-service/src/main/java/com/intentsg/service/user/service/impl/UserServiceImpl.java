package com.intentsg.service.user.service.impl;

import com.intentsg.model.ItemDto;
import com.intentsg.model.UserDto;
import com.intentsg.model.UserProfile;
import com.intentsg.model.exeptions.*;
import com.intentsg.service.user.entity.Item;
import com.intentsg.service.user.entity.User;
import com.intentsg.service.user.repository.ItemRepository;
import com.intentsg.service.user.repository.UserRepository;
import com.intentsg.service.user.service.UserService;
import com.intentsg.service.user.tools.CurrentUsers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;
    private final CurrentUsers currentUsers;
    private final PasswordEncoder encoder;


    @Autowired
    public UserServiceImpl( PasswordEncoder encoder, UserRepository userRepository, ModelMapper modelMapper, ItemRepository itemRepository, CurrentUsers currentUsers) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
        this.currentUsers = currentUsers;
        this.encoder = encoder;
    }

    @Override
    public UserDto getUserById(Long id) {
        if (id == null) throw new NotExistException("Id can't be null");
        User user = findById(id);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<Long> getToursIdByUserId(Long id) {
        validateUser(id);
        if (id == null) throw new NotExistException("Id can't be null");
        return itemRepository.findAllByUserId(id).stream()
                .map(Item::getTourId)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto addTourToCart(Long userId, Long tourId) {
        validateUser(userId);
        User user = findById(userId);
        if (user.getItems().stream().anyMatch(item -> item.getTourId().equals(tourId))) {
            throw new AlreadyExistsExeption("Tour with id: " + tourId + " already exists in users cart");
        }
        Item item = new Item();
        item.setUser(user);
        item.setTourId(tourId);
        return modelMapper.map(itemRepository.save(item), ItemDto.class);
    }

    @Override
    public void deleteTourFromCart(Long userId, Long tourId) {
        validateUser(userId);
        User user = findById(userId);
        Item item = user.getItems().stream().filter(item2 -> item2.getTourId().equals(tourId)).findFirst()
                .orElseThrow(() -> new NoSuchElementExeption("Tour with id: " + tourId + " not found in data base"));
        itemRepository.deleteById(item.getId());
    }

    @Override
    public void cleanCart(Long userId) {
        validateUser(userId);
        User user = findById(userId);
        itemRepository.deleteAll(user.getItems());
    }

    @Override
    public UserDto signUp(UserProfile userProfile) {
        if (userProfile == null) throw new NotExistException("UserProfile can't be null");
        if (userRepository.findUserByUserName(userProfile.getUserName()).isPresent()) {
            throw new WrongUserNameExeption("User with userName: " + userProfile.getUserName() + " already exists");
        }
        User user = modelMapper.map(userProfile, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setBalance(2000);
        user = userRepository.save(user);
        User currentUser = currentUsers.addUser(user);
        userRepository.save(currentUser);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto signIn(UserProfile userProfile) {
        if (userProfile == null) throw new NotExistException("UserProfile can't be null");
        User user = userRepository.
                findUserByUserName(userProfile.getUserName())
                .orElseThrow(() -> new WrongUserNameExeption("User with userName: " + userProfile.getUserName() + " not found"));
        if (encoder.matches(user.getPassword(), userProfile.getPassword())) {
            throw new WrongPasswordExeption("Password is wrong");
        }
        User currentUser = currentUsers.addUser(user);
        userRepository.save(currentUser);
        return modelMapper.map(currentUser, UserDto.class);
    }

    @Override
    public void signOut(Long userId) {
        validateUser(userId);
        currentUsers.removeUser(findById(userId));
    }

    private void validateUser(Long userId) {
        if (!currentUsers.checkExists(findById(userId))){
            throw new AccessDeniedException("Access denied, you need to log in");
        }
    }

    private User findById(Long userId){
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementExeption("User with id: " + userId + " not found in data base"));
    }

}
