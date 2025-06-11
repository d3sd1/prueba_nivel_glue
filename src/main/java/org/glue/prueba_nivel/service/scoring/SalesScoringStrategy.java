package org.glue.prueba_nivel.service.scoring;

import lombok.RequiredArgsConstructor;
import org.glue.prueba_nivel.database.entity.ProductEntity;

@RequiredArgsConstructor

public class SalesScoringStrategy implements ScoringStrategy {
    private final double weight;

    @Override
    public double score(ProductEntity product) {
        return product.getSalesUnits() * weight;
    }
}