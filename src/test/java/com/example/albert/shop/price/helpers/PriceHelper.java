package com.example.albert.shop.price.helpers;

import com.example.albert.shop.price.domain.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceHelper {

    public static Price createMockPrice(Integer brandId, Integer productId) {
        Price price = new Price();
        price.setProductId(productId);
        price.setBrandId(brandId);
        price.setStartDate(LocalDateTime.now());
        price.setEndDate(LocalDateTime.now());
        price.setPriceList(1);
        price.setPriority(0);
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurrency("EUR");

        return price;
    }
}