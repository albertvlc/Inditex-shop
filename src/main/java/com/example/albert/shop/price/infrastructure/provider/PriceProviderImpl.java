package com.example.albert.shop.price.infrastructure.provider;

import com.example.albert.shop.price.domain.Price;
import com.example.albert.shop.price.domain.provider.PriceProvider;
import com.example.albert.shop.price.infrastructure.entity.PriceEntity;
import com.example.albert.shop.price.infrastructure.jpa.PriceRepository;
import com.example.albert.shop.price.infrastructure.mapper.PriceMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceProviderImpl implements PriceProvider {
    private  PriceMapper priceMapper ;
    private  PriceRepository priceRepository;

    public PriceProviderImpl(PriceMapper priceMapper, PriceRepository priceRepository) {
        this.priceMapper = priceMapper;
        this.priceRepository = priceRepository;
    }
    public Price findByProductId(Integer productId) {
        PriceEntity priceEntity = priceRepository.findByProductId(productId);
        if (priceEntity == null) {
            return null;
        }

        return priceMapper.convertToDomain(priceEntity);
    }

}
