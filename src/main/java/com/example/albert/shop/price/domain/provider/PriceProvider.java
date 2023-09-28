package com.example.albert.shop.price.domain.provider;

import com.example.albert.shop.price.domain.Price;

public interface PriceProvider {
    Price findByProductId(Integer productId);

}
