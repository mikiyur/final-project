package com.intentsg.service.tour.controller;


import com.intentsg.model.TourDto;
import com.intentsg.service.tour.entity.Tour;
import com.intentsg.service.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/tours")
public class TourController {


    private TourService tourService;
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;
    private final int TOURS_PER_PAGE = 9;
    private final String MIN_PRICE = "0";
    private final String MAX_PRICE = "99999";

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
        Page<Tour> page = tourService.getAllTour(minPrice, maxPrice, pageable); //todo max-minPrice default value
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
