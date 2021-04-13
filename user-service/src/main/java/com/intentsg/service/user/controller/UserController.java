package com.intentsg.service.user.controller;

import com.intentsg.model.ItemDto;
import com.intentsg.model.UserDto;
import com.intentsg.model.UserProfile;
import com.intentsg.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserService userService;
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;

    @Autowired
    public UserController(UserService userService, DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.userService = userService;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    private ServiceInstance getServiceInstance() {
        return discoveryClient.getInstances("edge-service").get(0);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok("users test");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<List<Long>> getToursIdByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getToursIdByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/cart/add")
    public ResponseEntity<ItemDto> addTourToCart(@RequestParam(name = "userid") Long userId,
                                                 @RequestParam(name = "tourid") Long tourId) {
        return new ResponseEntity<>(userService.addTourToCart(userId, tourId), HttpStatus.OK);
    }

    @GetMapping("/cart/delete")
    public ResponseEntity<String> deleteTourFromCart(@RequestParam(name = "userid") Long userId,
                                                     @RequestParam(name = "tourid") Long tourId) {
        userService.deleteTourFromCart(userId, tourId);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/cart/clean")
    public ResponseEntity<String> cleanCart(@RequestParam(name = "userid") Long userId) {
        userService.cleanCart(userId);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserProfile userProfile) {
        return new ResponseEntity<>(userService.signUp(userProfile), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDto> signIn(@Valid @RequestBody UserProfile userProfile) {
        return new ResponseEntity<>(userService.signIn(userProfile), HttpStatus.OK);
    }

    @GetMapping("/signout")
    public ResponseEntity signOut(){
        userService.signOut();
        return ResponseEntity.ok("success");
    }
}
