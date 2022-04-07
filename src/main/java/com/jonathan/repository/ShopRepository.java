package com.jonathan.repository;

import com.jonathan.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    public Shop findByIdentifier(String identifier);
}
