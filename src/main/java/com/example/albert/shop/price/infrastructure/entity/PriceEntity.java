package com.example.albert.shop.price.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "Prices")
@Data
public class PriceEntity {
    @Id
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Integer priceList;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "currency")
    private String currency;

    public PriceEntity() {
    }

    public PriceEntity( Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList,
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








