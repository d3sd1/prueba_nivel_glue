package org.glue.prueba_nivel.controller;

import org.glue.prueba_nivel.controller.dto.request.SortRequestDTO;
import org.glue.prueba_nivel.controller.dto.response.ProductResponseDTO;
import org.glue.prueba_nivel.mapper.ProductMapper;
import org.glue.prueba_nivel.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    void whenValidRequest_thenReturnSortedProducts() throws Exception {
        // Given
        double salesWeight = 0.5;
        double stockWeight = 0.5;
        // Prepare dummy service output
        var product1 = new org.glue.prueba_nivel.database.entity.Product("10", "Shirt A", 5, List.of());
        var product2 = new org.glue.prueba_nivel.database.entity.Product("20", "Shirt B", 3, List.of());
        List<org.glue.prueba_nivel.database.entity.Product> serviceResult = List.of(product1, product2);
        when(productService.sortProducts(eq(salesWeight), eq(stockWeight))).thenReturn(serviceResult);
        // Prepare mapping of domain products to DTOs
        ProductResponseDTO dto1 = new ProductResponseDTO();
        dto1.setId(10);
        dto1.setName("Shirt A");
        dto1.setSalesUnits(5);
        dto1.setStocks(List.of());
        ProductResponseDTO dto2 = new ProductResponseDTO();
        dto2.setId(20);
        dto2.setName("Shirt B");
        dto2.setSalesUnits(3);
        dto2.setStocks(List.of());
        when(productMapper.toProductResponseDto(product1)).thenReturn(dto1);
        when(productMapper.toProductResponseDto(product2)).thenReturn(dto2);

        // When & Then: perform POST request with valid weights
        String jsonRequest = "{\"salesWeight\":0.5,\"stockRatioWeight\":0.5}";
        mockMvc.perform(post("/api/products/sorted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].name").value("Shirt A"))
                .andExpect(jsonPath("$[1].id").value(20));

        // Verify that service was called with correct weights
        verify(productService).sortProducts(eq(0.5), eq(0.5));
        // Verify that mapper was called for each product
        verify(productMapper, times(2)).toProductResponseDto(ArgumentMatchers.any());
    }

    @Test
    void whenInvalidRequest_thenReturnBadRequest() throws Exception {
        // salesWeight = 0 (invalid, must be > 0)
        String invalidJson = "{\"salesWeight\":0.0,\"stockRatioWeight\":0.5}";
        mockMvc.perform(post("/api/products/sorted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());

        // Service should not be called on validation error
        verify(productService, never()).sortProducts(anyDouble(), anyDouble());
    }
}
