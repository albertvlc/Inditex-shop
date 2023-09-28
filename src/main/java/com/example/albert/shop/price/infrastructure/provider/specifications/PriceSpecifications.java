package com.example.albert.shop.price.infrastructure.provider.specifications;

import com.example.albert.shop.price.infrastructure.entity.PriceEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class PriceSpecifications {
    public static Specification<PriceEntity> withProductId(Integer productId) {
        return withFieldEquals("productId", productId);

    }

    public static Specification<PriceEntity> withBrandId(Integer brandId) {
        return withFieldEquals("brandId", brandId);

    }

    public static Specification<PriceEntity> withApplicationDate(LocalDateTime applicationDate) {
        return (root, query, builder) -> {
            if (applicationDate != null) {
                return builder.greaterThanOrEqualTo(root.get("startDate"), applicationDate);
            }
            return null;
        };
    }

    public static Specification<PriceEntity> withFieldEquals(String fieldName, Object value) {
        return (root, query, builder) -> {
            if (value != null) {
                return builder.equal(root.get(fieldName), value);
            }
            return null;
        };
    }

}
