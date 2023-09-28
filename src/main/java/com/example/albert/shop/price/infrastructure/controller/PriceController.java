package com.example.albert.shop.price.infrastructure.controller;

import com.example.albert.shop.price.application.PriceService;
import com.example.albert.shop.price.domain.Price;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceService priceService;
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    @GetMapping("/by-product-id")
    public ResponseEntity<Price> getPriceByProductId(
            @RequestParam Integer productId){
        Price price = priceService.getPriceByProductId(productId);
        return ResponseEntity.ok(price);
    }
    /*
        curl --location 'http://localhost:8080/prices/by-product-id?productId=35455'
    */

}
