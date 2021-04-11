package com.intentsg.service.tour.controller;



import com.intentsg.service.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/tours")
public class TourController {


    private TourService tourService;
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;

    @Autowired
    public TourController(TourService tourService, DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.tourService = tourService;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    private ServiceInstance getServiceInstance(){
        return discoveryClient.getInstances("edge-service").get(0);
    }

	@GetMapping("/test")
	public ResponseEntity test() {
		return ResponseEntity.ok("tour-service");
	}

}
