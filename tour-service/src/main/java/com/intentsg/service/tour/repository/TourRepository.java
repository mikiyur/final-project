package com.intentsg.service.tour.repository;


import com.intentsg.service.tour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
