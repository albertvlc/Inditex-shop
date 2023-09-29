package com.example.albert.shop.price.controller;

import com.example.albert.shop.price.application.PriceService;
import com.example.albert.shop.price.domain.Price;
import com.example.albert.shop.price.infrastructure.controller.PriceController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.mockito.ArgumentMatchers.any;
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

    @Autowired
    private ObjectMapper objectMapper;


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
                .andExpect(jsonPath("$.productId", is(productId)))
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
                .andExpect(jsonPath("$[0].productId", is(productId)))
                .andExpect(jsonPath("$[0].price", is(35.50)))
                .andExpect(jsonPath("$[0].currency", is("EUR")));
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
                .andExpect(jsonPath("$[0].productId", is(productId)))
                .andExpect(jsonPath("$[0].price", is(35.50)))
                .andExpect(jsonPath("$[0].currency", is("EUR")));
    }

    @Test
    public void shouldGetPriceByOptionalParametersWithMissingParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices/by-optional-params")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetPriceByOptionalParametersWithParamsAndParamsNulls() throws Exception {
        List<Price> mockPrices = new ArrayList<>();
        Price expectedPrice = createMockPrice(productId, brandId);
        mockPrices.add(expectedPrice);


        when(priceService.getPriceByOptionalParameters(productId, brandId, null))
                .thenReturn(mockPrices);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices/by-optional-params")
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId", is(productId)))
                .andExpect(jsonPath("$[0].price", is(35.50)))
                .andExpect(jsonPath("$[0].currency", is("EUR")));
    }

    @Test
    public void shouldGetPriceByOptionalParametersWithEmptyResult() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceService.getPriceByOptionalParameters(productId, brandId, applicationDate))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/prices/by-optional-params")
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("applicationDate", "2020-06-14")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreatePrice() throws Exception {
        Price price = createMockPrice(productId,brandId);

        when(priceService.createPrice(any(Price.class))).thenReturn(price);

        mockMvc.perform(MockMvcRequestBuilders.post("/prices/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(price)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.price").value(35.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

}
