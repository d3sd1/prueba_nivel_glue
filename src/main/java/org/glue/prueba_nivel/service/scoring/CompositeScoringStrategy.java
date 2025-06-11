package org.glue.prueba_nivel.service.scoring;

import org.glue.prueba_nivel.database.entity.ProductEntity;

import java.util.List;

public class CompositeScoringStrategy implements ScoringStrategy {
    private final List<ScoringStrategy> strategies;

    public CompositeScoringStrategy(List<ScoringStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public double score(ProductEntity product) {
        return strategies.stream().mapToDouble(s -> s.score(product)).sum();
    }
}