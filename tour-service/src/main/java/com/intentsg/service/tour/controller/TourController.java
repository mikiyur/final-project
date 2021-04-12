package com.intentsg.service.tour.controller;


import com.intentsg.model.TourDto;
import com.intentsg.service.tour.entity.Tour;
import com.intentsg.service.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
@RequestMapping("/tours")
public class TourController {

    private TourService tourService;
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;
    private final int TOURS_PER_PAGE = 9;
    private final String MIN_PRICE = "0";
    private final String MAX_PRICE = "99999";
    private Object ParameterizedTypeReference;

    @Autowired
    public TourController(TourService tourService, DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.tourService = tourService;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    private ServiceInstance getServiceInstance() {
        return discoveryClient.getInstances("edge-service").get(0);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok("tour-service");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDto> getTourById(@PathVariable Long id) {
        TourDto tourDto = tourService.getTourDtoById(id);
        return new ResponseEntity<>(tourDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Page> getAllTours(@RequestParam(name = "minPrice", defaultValue = MIN_PRICE) Integer minPrice,
                                            @RequestParam(name = "maxPrice", defaultValue = MAX_PRICE) Integer maxPrice,
                                            @PageableDefault(size = TOURS_PER_PAGE) Pageable pageable) {
        System.out.println(pageable);
        Page<Tour> page = tourService.getAllTour(minPrice, maxPrice, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<List<TourDto>> saveTours(@RequestBody(required = false) List<TourDto> tourDtoList) {
        return new ResponseEntity<>(tourService.saveTours(tourDtoList), HttpStatus.OK);
    }

    @GetMapping("/userid/{id}")
    public ResponseEntity<List<TourDto>> getUserTours(@PathVariable Long id) {
        HttpEntity<Long> longHttpEntity = new HttpEntity<>(0L);
        ResponseEntity<List<Long>> result = restTemplate.exchange(
                getServiceInstance().getUri().toString() + "api/users/tours/" + id,
                HttpMethod.GET,
                longHttpEntity,
                new ParameterizedTypeReference<List<Long>>() {
                });
        return new ResponseEntity<>(tourService.getAllUserTours(result.getBody()), HttpStatus.OK);
    }
}
