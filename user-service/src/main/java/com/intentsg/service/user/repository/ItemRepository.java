package com.intentsg.service.user.repository;

import com.intentsg.service.user.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByUserId(Long id);

//    @Query(value = "delete from items where user_id = :userId and tour_id = :tourId")
//    void deleteItem(@Param("userId") Long userId, @Param("tourId") Long tourId);
//
//    @Query(value = "delete from items as item where item.user_id = :userId", nativeQuery = true)
//    void removeItemsByUserId(@Param("userId") Long userId);
}

