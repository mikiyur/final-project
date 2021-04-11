package com.intentsg.service.tour.service;

import com.intentsg.model.TourDto;
import com.intentsg.service.tour.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface TourService {
    TourDto getTourDtoById(long id);

    Page<Tour> getAllTour(Integer minPrice, Integer maxPrice, Pageable pageable);
    }
