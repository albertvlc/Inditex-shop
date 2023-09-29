package com.example.albert.shop.price.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Price {

    private Integer productId;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer priority;
    private BigDecimal price;
    private String currency;

    public Price() {
    }

    public Price( Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList,
                 Integer priority, BigDecimal price, String currency) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

}



