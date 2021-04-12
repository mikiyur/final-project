package com.intentsg.service.tour.repository;


import com.intentsg.service.tour.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    Optional<Tour> findById (long id);
    @Query(value = "select tour from tours as tour where price between :minPrice and :maxPrice")
    Page<Tour> getAllByPrice(@Param("minPrice")int minPrice, @Param("maxPrice")int maxPrice, Pageable pageable);
}