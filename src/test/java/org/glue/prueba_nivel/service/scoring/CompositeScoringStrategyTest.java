package org.glue.prueba_nivel.service.scoring;

import org.glue.prueba_nivel.database.entity.Product;
import org.glue.prueba_nivel.service.scoring.CompositeScoringStrategy;
import org.glue.prueba_nivel.service.scoring.ScoringStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompositeScoringStrategyTest {

    @Test
    void score_returnsSumOfAllStrategyScores() {
        // Create two simple scoring strategies for testing
        ScoringStrategy strat1 = product -> 5.0;  // constant score 5
        ScoringStrategy strat2 = product -> 2.5;  // constant score 2.5

        CompositeScoringStrategy composite = new CompositeScoringStrategy(List.of(strat1, strat2));
        Product dummy = new Product("X", "Dummy", 0, null);

        double result = composite.score(dummy);

        // The composite score should be the sum of both strategy scores (5.0 + 2.5 = 7.5)
        assertEquals(7.5, result, 0.0001);
    }

    @Test
    void score_withNoStrategies_returnsZero() {
        CompositeScoringStrategy composite = new CompositeScoringStrategy(List.of());
        Product dummy = new Product("X", "Dummy", 0, null);

        double result = composite.score(dummy);

        // No strategies -> score should be 0
        assertEquals(0.0, result, 0.0001);
    }
}
