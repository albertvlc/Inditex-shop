package com.example.albert.shop.price.infrastructure.controller;

import com.example.albert.shop.price.application.PriceService;
import com.example.albert.shop.price.domain.Price;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

    @GetMapping("/by-params")
    public ResponseEntity<List<Price>> getPriceByParameters(
            @RequestParam("productId") Integer productId,
            @RequestParam("brandId") Integer brandId,
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    ) {

        List<Price> prices = priceService.getPriceByParameters(productId, brandId, applicationDate);

        if (prices.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(prices);
    }

    /*
`        curl --location 'http://localhost:8080/prices/by-params?productId=35455&brandId=1&applicationDate=2020-06-14T00:00:00'
`    */

    @GetMapping("/by-optional-params")
    public ResponseEntity<List<Price>> getPriceByOptionalParameters(
            @RequestParam(name = "productId", required = false) Integer productId,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "applicationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate applicationDate
    ) {
        if (productId == null && brandId == null && applicationDate == null) {
            return ResponseEntity.badRequest().build();
        }
        LocalDateTime date = null;
        if( applicationDate != null ) {
            date = LocalDateTime.of(applicationDate, LocalTime.now());
        }
        // Trick para adicionar la fecha en yyyy-MM-dd, no es la mejor manera de implementar la solución si queremos ser muy especificos con el tiempo
        List<Price> prices = priceService.getPriceByOptionalParameters(productId, brandId,date);

        if (prices.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(prices);
    }

    /*
        curl --location 'http://localhost:8080/prices/by-optional-params?applicationDate=2020-06-14'
    */

}
