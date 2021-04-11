package com.intentsg.service.tour.service.impl;

import com.intentsg.model.TourDto;
import com.intentsg.service.tour.entity.Tour;
import com.intentsg.service.tour.exeption.NoSuchElementExeption;
import com.intentsg.service.tour.repository.TourRepository;
import com.intentsg.service.tour.service.TourService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TourServiceImpl implements TourService {

    private TourRepository tourRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, ModelMapper modelMapper) {
        this.tourRepository = tourRepository;
        this.modelMapper = modelMapper;
    }

    public TourDto getTourDtoById(long id){
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementExeption("Tour with id: "+ id +" not found in data base"));
        return modelMapper.map(tour, TourDto.class);
    }

}
