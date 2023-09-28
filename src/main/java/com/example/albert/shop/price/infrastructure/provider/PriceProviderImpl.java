package com.example.albert.shop.price.infrastructure.provider;

import com.example.albert.shop.price.domain.Price;
import com.example.albert.shop.price.domain.provider.PriceProvider;
import com.example.albert.shop.price.infrastructure.entity.PriceEntity;
import com.example.albert.shop.price.infrastructure.jpa.PriceRepository;
import com.example.albert.shop.price.infrastructure.mapper.PriceMapper;
import com.example.albert.shop.price.infrastructure.provider.specifications.PriceSpecifications;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceProviderImpl implements PriceProvider {
    private  PriceMapper priceMapper ;
    private  PriceRepository priceRepository;

    public PriceProviderImpl(PriceMapper priceMapper, PriceRepository priceRepository) {
        this.priceMapper = priceMapper;
        this.priceRepository = priceRepository;
    }
    @Override
    public Price findByProductId(Integer productId) {
        PriceEntity priceEntity = priceRepository.findByProductId(productId);
        if (priceEntity == null) {
            return null;
        }

        return priceMapper.convertToDomain(priceEntity);
    }

    @Override
    public List<Price> findPricesByParameters(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        List<PriceEntity> listPriceEntity = priceRepository.findPricesByProductIdAndBrandIdAndEndDateGreaterThanEqual(
                productId,
                brandId,
                applicationDate
        );

        return listPriceEntity.stream()
                .map(priceMapper::convertToDomain)
                .collect(Collectors.toList());

    }

    @Override
    public List<Price> findPricesByOptionalParameters(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        Specification<PriceEntity> criteria = Specification.where(PriceSpecifications.withProductId(productId))
                .and(PriceSpecifications.withBrandId(brandId))
                .and(PriceSpecifications.withApplicationDate(applicationDate));

        List<PriceEntity> listPriceEntity = priceRepository.findAll(criteria);

        return listPriceEntity.stream()
                .map(priceMapper::convertToDomain)
                .collect(Collectors.toList());
    }

}
