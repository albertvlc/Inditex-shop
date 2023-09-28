package com.example.albert.shop.price.controller;

import com.example.albert.shop.price.application.PriceService;
import com.example.albert.shop.price.infrastructure.controller.PriceController;
import java.time.LocalDateTime;
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
import static com.example.albert.shop.price.helpers.PriceHelper.createMockPricesForTestByDate;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    public void shouldReturnPricesForProductIdXWhenGetPriceByParamsAt10AM() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceService.getPriceByParameters(productId, brandId, applicationDate))
                .thenReturn(createMockPricesForTestByDate(applicationDate, LocalDateTime.of(2020, 7, 14, 10, 0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/prices/by-params")
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].price", is(35.50)))
                .andExpect(jsonPath("$[0].curr", is("EUR")));
    }

    @Test
    public void shouldReturnPricesForProductIdXWhenGetPriceByParamsAt21PM() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 21, 10, 0);

        when(priceService.getPriceByParameters(productId, brandId, applicationDate))
                .thenReturn(createMockPricesForTestByDate(applicationDate, LocalDateTime.of(2020, 7, 14, 10, 0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/prices/by-params")
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("applicationDate", applicationDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].price", is(35.50)))
                .andExpect(jsonPath("$[0].curr", is("EUR")));
    }


}
