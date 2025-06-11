package org.glue.prueba_nivel.service.scoring;

import org.glue.prueba_nivel.database.entity.Product;
import org.glue.prueba_nivel.service.scoring.StockRatioScoringStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockRatioScoringStrategyTest {

    @Test
    void score_allSizesInStock_returnsWeightTimesOne() {
        Product product = new Product("X", "AllStockProduct", 0, List.of(
                new Product.Stock("S", 5),
                new Product.Stock("M", 1),
                new Product.Stock("L", 10)
        ));
        StockRatioScoringStrategy strategy = new StockRatioScoringStrategy(0.8);

        double score = strategy.score(product);

        // All 3 sizes have stock, ratio = 3/3 = 1. Score = 1 * weight.
        assertEquals(0.8, score, 0.0001);
    }

    @Test
    void score_noSizeInStock_returnsZero() {
        Product product = new Product("X", "NoStockProduct", 0, List.of(
                new Product.Stock("S", 0),
                new Product.Stock("M", 0),
                new Product.Stock("L", 0)
        ));
        StockRatioScoringStrategy strategy = new StockRatioScoringStrategy(1.0);

        double score = strategy.score(product);

        // No sizes in stock, ratio = 0, score should be 0 regardless of weight
        assertEquals(0.0, score, 0.0001);
    }

    @Test
    void score_someSizesInStock_returnsFractionOfWeight() {
        Product product = new Product("X", "PartialStockProduct", 0, List.of(
                new Product.Stock("S", 10),
                new Product.Stock("M", 0),
                new Product.Stock("L", 7)
        ));
        StockRatioScoringStrategy strategy = new StockRatioScoringStrategy(1.0);

        double score = strategy.score(product);

        // 2 out of 3 sizes have stock, ratio = 2/3 ≈ 0.6667, score = 0.6667 * weight(1.0)
        assertEquals(0.6667, score, 0.0001);
    }
}
