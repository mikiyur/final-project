package com.intentsg.service.tour.service;

import com.intentsg.model.TourDto;
import com.intentsg.service.tour.entity.Tour;

public interface TourService {
    TourDto getTourDtoById(long id);
}
