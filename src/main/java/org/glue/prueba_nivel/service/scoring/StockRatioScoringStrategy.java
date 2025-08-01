package org.glue.prueba_nivel.service.scoring;

import org.glue.prueba_nivel.database.entity.Product;

public class StockRatioScoringStrategy implements ScoringStrategy {
    private final double weight;
    public StockRatioScoringStrategy(double weight) { this.weight = weight; }

    @Override
    public double score(Product product) {
        long sizesInStock = product.getStocks().stream().filter(s -> s.getUnits() > 0).count();
        return (sizesInStock / 3.0) * weight;
    }
}