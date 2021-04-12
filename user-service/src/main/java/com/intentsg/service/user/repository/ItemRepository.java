package com.intentsg.service.user.repository;

import com.intentsg.service.user.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByUserId(Long id);
}
