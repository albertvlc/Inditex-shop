package com.example.albert.shop.price.application;

import com.example.albert.shop.price.domain.Price;
import com.example.albert.shop.price.domain.provider.PriceProvider;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    private final PriceProvider priceProvider;
    public PriceService(PriceProvider priceProvider) {
        this.priceProvider = priceProvider;
    }

    public Price getPriceByProductId(Integer productId) {

        return priceProvider.findByProductId(productId);

    }
}