package org.glue.prueba_nivel.service.scoring;

import org.glue.prueba_nivel.database.entity.Product;
import org.glue.prueba_nivel.service.scoring.CompositeScoringStrategy;
import org.glue.prueba_nivel.service.scoring.ScoringStrategy;
import org.glue.prueba_nivel.service.scoring.ScoringStrategyFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoringStrategyFactoryTest {

    @Test
    void create_returnsCompositeStrategyWithSalesAndStock() {
        double salesWeight = 0.7;
        double stockWeight = 0.5;
        ScoringStrategy strategy = ScoringStrategyFactory.create(salesWeight, stockWeight);

        // The returned strategy should be a CompositeScoringStrategy containing two sub-strategies
        assertTrue(strategy instanceof CompositeScoringStrategy);

        // Test that the composite strategy's scoring matches the sum of individual scores
        Product product = new Product("X", "TestProduct", 20, List.of(
                new Product.Stock("S", 1),
                new Product.Stock("M", 1),
                new Product.Stock("L", 0)
        ));
        // Expected score = salesUnits*salesWeight + (sizesInStock/3)*stockWeight
        // sizesInStock = 2, so stock ratio = 2/3 ≈ 0.6667
        double expectedScore = 20 * salesWeight + (2.0/3.0) * stockWeight;
        assertEquals(expectedScore, strategy.score(product), 0.0001);
    }
}
