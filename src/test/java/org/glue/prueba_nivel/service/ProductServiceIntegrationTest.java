package org.glue.prueba_nivel.service;

import org.glue.prueba_nivel.database.entity.Product;
import org.glue.prueba_nivel.database.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void whenEqualWeights_thenReturnsProductsSortedByCombinedScore() {
        List<Product> sorted = productService.sortProducts(1.0, 1.0);

        assertNotNull(sorted);
        assertFalse(sorted.isEmpty());
        assertEquals(6, sorted.size());
        // With equal weights, product with highest sales (ID "5") should be first, lowest combined score (ID "4") last
        assertEquals("5", sorted.get(0).getId());
        assertEquals("4", sorted.get(sorted.size() - 1).getId());
    }

    @Test
    void whenStockWeightGreatlyDominant_thenProductsOrderReflectsStockPriority() {
        // Use a very low sales weight and high stock weight to emphasize stock ratio
        List<Product> sorted = productService.sortProducts(0.01, 1.0);

        assertEquals(6, sorted.size());
        // In this scenario, a product with full stock and slightly lower sales (ID "3") should come before a product with higher sales but partial stock (ID "1")
        int indexProd3 = indexOfProduct(sorted, "3");
        int indexProd1 = indexOfProduct(sorted, "1");
        assertTrue(indexProd3 < indexProd1, "Product with ID 3 should come before product with ID 1");
        // Also ensure product with ID "5" (highest sales) remains first due to its very large sales number
        assertEquals("5", sorted.get(0).getId());
    }

    // Helper to find index of product by ID in list
    private int indexOfProduct(List<Product> list, String productId) {
        for (int i = 0; i < list.size(); i++) {
            if (productId.equals(list.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
}
