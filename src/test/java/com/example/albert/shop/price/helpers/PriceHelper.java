package com.example.albert.shop.price.helpers;

import com.example.albert.shop.price.domain.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Price> createMockPricesForTest() {
        Integer productId = 35455;
        Integer brandId = 1;

        List<Price> mockPrices = new ArrayList<>();

        Price price = new Price();
        price.setProductId(productId);
        price.setBrandId(brandId);
        price.setStartDate(LocalDateTime.now());
        price.setEndDate(LocalDateTime.now());
        price.setPriceList(1);
        price.setProductId(productId);
        price.setPriority(0);
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurrency("EUR");

        mockPrices.add(price);

        return mockPrices;
    }

    public static List<Price> createMockPricesForTestByDate(LocalDateTime startDate, LocalDateTime endDate) {
        Integer productId = 35455;
        Integer brandId = 1;
        List<Price> mockPrices = new ArrayList<>();

        Price price = new Price();
        price.setProductId(productId);
        price.setBrandId(brandId);
        price.setStartDate(startDate);
        price.setEndDate(endDate);
        price.setPriceList(1);
        price.setProductId(productId);
        price.setPriority(0);
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurrency("EUR");

        mockPrices.add(price);

        return mockPrices;
    }
}