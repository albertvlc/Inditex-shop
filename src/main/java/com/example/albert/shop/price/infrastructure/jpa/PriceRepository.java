package com.example.albert.shop.price.infrastructure.jpa;

import com.example.albert.shop.price.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer>{
    PriceEntity findByProductId(Integer productId);

}
