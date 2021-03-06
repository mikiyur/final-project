package com.intentsg.service.tour.service.impl;

import com.intentsg.model.TourDto;
import com.intentsg.model.exeptions.NoSuchElementExeption;
import com.intentsg.service.tour.entity.Tour;
import com.intentsg.service.tour.repository.TourRepository;
import com.intentsg.service.tour.service.TourService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<Tour> getAllTour(Integer minPrice, Integer maxPrice, Pageable pageable) {
        Page <Tour> tour = tourRepository.getAllByPrice(minPrice, maxPrice, pageable);
        return tour;
    }

    @Override
    public List<TourDto> saveTours(List<TourDto> tourDtoList){
        List<Tour> toursList = tourDtoList.stream()
                .map(tourDto -> modelMapper.map(tourDto, Tour.class))
                .collect(Collectors.toList());
        toursList.forEach(tour -> tour.setId(null));
        return tourRepository.saveAll(toursList).stream()
                .map(tour -> modelMapper.map(tour, TourDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDto> getAllUserTours(List<Long> idTours){
        return tourRepository.findAllById(idTours).stream()
                .map(tour -> modelMapper.map(tour, TourDto.class))
                .collect(Collectors.toList());
    }
}
