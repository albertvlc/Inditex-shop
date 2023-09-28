package com.example.albert.shop.price.infrastructure.mapper;

import com.example.albert.shop.price.domain.Price;
import com.example.albert.shop.price.infrastructure.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
    public Price convertToDomain(PriceEntity priceEntity) {
        Price price = new Price();
        price.setProductId(priceEntity.getProductId());
        price.setBrandId(priceEntity.getBrandId());
        price.setStartDate(priceEntity.getStartDate());
        price.setEndDate(priceEntity.getEndDate());
        price.setPriceList(priceEntity.getPriceList());
        price.setProductId(priceEntity.getProductId());
        price.setPriority(priceEntity.getPriority());
        price.setPrice(priceEntity.getPrice());
        price.setCurrency(priceEntity.getCurrency());
        return price;
    }

    public PriceEntity convertToEntity(Price price) {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setProductId(price.getProductId());
        priceEntity.setBrandId(price.getBrandId());
        priceEntity.setStartDate(price.getStartDate());
        priceEntity.setEndDate(price.getEndDate());
        priceEntity.setPriceList(price.getPriceList());
        priceEntity.setProductId(price.getProductId());
        priceEntity.setPriority(price.getPriority());
        priceEntity.setPrice(price.getPrice());
        priceEntity.setCurrency(price.getCurrency());
        return priceEntity;
    }
}