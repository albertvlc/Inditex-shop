package com.example.albert.shop.price.application;

import com.example.albert.shop.price.domain.Price;
import com.example.albert.shop.price.domain.provider.PriceProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.example.albert.shop.price.helpers.PriceHelper.createMockPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceServiceTest {

    private PriceProvider priceProvider;
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        priceProvider = Mockito.mock(PriceProvider.class);
        priceService = new PriceService(priceProvider);
    }

    Integer productId = 1;
    Integer brandId = 2;
    Price expectedPrice = createMockPrice(brandId, productId);

    @Test
    public void shouldReturnPriceForProductIdWhenGetPriceByProductId() {

        Mockito.when(priceProvider.findByProductId(productId)).thenReturn(expectedPrice);

        Price actualPrice = priceService.getPriceByProductId(productId);

        assertEquals(expectedPrice, actualPrice);
    }
}
