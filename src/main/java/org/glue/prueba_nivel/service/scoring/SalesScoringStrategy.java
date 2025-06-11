package org.glue.prueba_nivel.service.scoring;

import lombok.RequiredArgsConstructor;
import org.glue.prueba_nivel.database.entity.Product;

@RequiredArgsConstructor

public class SalesScoringStrategy implements ScoringStrategy {
    private final double weight;

    @Override
    public double score(Product product) {
        return product.getSalesUnits() * weight;
    }
}