package org.glue.prueba_nivel.service;

import org.glue.prueba_nivel.database.entity.Product;
import org.glue.prueba_nivel.database.repository.ProductRepository;
import org.glue.prueba_nivel.service.ProductSortingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductSortingServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductSortingServiceImpl productService;

    @Test
    void whenSalesWeightDominant_thenSortBySalesDesc() {
        // Given two products: one with higher sales but lower stock ratio, another with lower sales but higher stock ratio
        Product pHighSales = new Product("A", "HighSalesProduct", 15, List.of(
                new Product.Stock("S", 0), new Product.Stock("M", 0), new Product.Stock("L", 5)  // only 1 size in stock (L)
        ));
        Product pHighStock = new Product("B", "HighStockProduct", 10, List.of(
                new Product.Stock("S", 1), new Product.Stock("M", 1), new Product.Stock("L", 1)  // all 3 sizes in stock
        ));
        when(productRepository.findAll()).thenReturn(List.of(pHighSales, pHighStock));

        // When sales weight is much higher than stock weight
        List<Product> result = productService.sortProducts(0.9, 0.1);

        // Then the product with higher salesUnits should come first
        assertEquals("A", result.get(0).getId());
        assertEquals("B", result.get(1).getId());
    }

    @Test
    void whenStockWeightDominant_thenSortByStockDesc() {
        // Given the same two products as above
        Product pHighSales = new Product("A", "HighSalesProduct", 15, List.of(
                new Product.Stock("S", 0), new Product.Stock("M", 0), new Product.Stock("L", 5)
        ));
        Product pHighStock = new Product("B", "HighStockProduct", 10, List.of(
                new Product.Stock("S", 1), new Product.Stock("M", 1), new Product.Stock("L", 1)
        ));
        when(productRepository.findAll()).thenReturn(List.of(pHighSales, pHighStock));

        // When stock weight is much higher than sales weight
        List<Product> result = productService.sortProducts(0.1, 0.9);

        // Then the product with higher stock ratio should come first
        assertEquals("B", result.get(0).getId());
        assertEquals("A", result.get(1).getId());
    }
}
