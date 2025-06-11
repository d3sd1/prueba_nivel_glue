package org.glue.prueba_nivel.service.scoring;

import java.util.List;

public class ScoringStrategyFactory {
    public static ScoringStrategy create(double salesWeight, double stockWeight) {
        return new CompositeScoringStrategy(List.of(
                new SalesScoringStrategy(salesWeight),
                new StockRatioScoringStrategy(stockWeight)
        ));
    }
}