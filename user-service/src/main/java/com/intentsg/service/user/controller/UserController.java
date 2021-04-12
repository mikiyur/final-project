package com.intentsg.service.user.controller;

import com.intentsg.model.UserDto;
import com.intentsg.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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

}
