package com.example.albert.shop.price.domain.provider;

import com.example.albert.shop.price.domain.Price;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceProvider {
    Price findByProductId(Integer productId);

    List<Price> findPricesByParameters(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
