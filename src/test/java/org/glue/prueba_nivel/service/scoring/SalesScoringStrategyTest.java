package org.glue.prueba_nivel.service.scoring;

import org.glue.prueba_nivel.database.entity.Product;
import org.glue.prueba_nivel.service.scoring.SalesScoringStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesScoringStrategyTest {

    @Test
    void score_withWeight1_returnsSalesUnits() {
        Product product = new Product("X", "TestProduct", 40, null);
        SalesScoringStrategy strategy = new SalesScoringStrategy(1.0);

        double score = strategy.score(product);

        assertEquals(40.0, score, 0.0001);
    }

    @Test
    void score_withHalfWeight_returnsHalfOfSalesUnits() {
        Product product = new Product("X", "TestProduct", 40, null);
        SalesScoringStrategy strategy = new SalesScoringStrategy(0.5);

        double score = strategy.score(product);

        assertEquals(20.0, score, 0.0001);
    }
}
