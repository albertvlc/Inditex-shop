package com.example.albert.shop.price.controller;

import com.example.albert.shop.price.application.PriceService;
import com.example.albert.shop.price.infrastructure.controller.PriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.albert.shop.price.helpers.PriceHelper.createMockPrice;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class PriceControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PriceService priceService;
    private Integer brandId = 1;
    private Integer productId = 35455;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PriceController(priceService)).build();
    }

    @Test
    public void shouldReturnPriceForProductIdWhenGetPriceByProductId() throws Exception {

        when(priceService.getPriceByProductId(productId))
                .thenReturn(createMockPrice(productId, brandId));

        mockMvc.perform(MockMvcRequestBuilders.get("/prices/by-product-id")
                .param("productId", productId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.price", is(35.50)))
                .andExpect(jsonPath("$.currency", is("EUR")));
    }

}
