package org.glue.prueba_nivel.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductSortingServiceImpl implements ProductService {

    private List<Product> mockProducts() {
        return List.of(
                new Product(1, "V-NECK BASIC SHIRT", 100, Map.of("S", 4, "M", 9, "L", 0)),
                new Product(2, "CONTRASTING FABRIC T-SHIRT", 50, Map.of("S", 35, "M", 9, "L", 9)),
                new Product(3, "RAISED PRINT T-SHIRT", 80, Map.of("S", 20, "M", 2, "L", 20)),
                new Product(4, "PLEATED T-SHIRT", 3, Map.of("S", 25, "M", 30, "L", 10)),
                new Product(5, "CONTRASTING LACE T-SHIRT", 650, Map.of("S", 0, "M", 1, "L", 0)),
                new Product(6, "SLOGAN T-SHIRT", 20, Map.of("S", 9, "M", 2, "L", 5))
        );
    }

    @Override
    public List<Product> sortProducts(double salesWeight, double stockRatioWeight) {
        return mockProducts().stream()
                .sorted((p1, p2) -> Double.compare(score(p2, salesWeight, stockRatioWeight), score(p1, salesWeight, stockRatioWeight)))
                .collect(Collectors.toList());
    }

    private double score(Product product, double salesWeight, double stockRatioWeight) {
        double stockRatio = product.getStocks().values().stream()
                .filter(stock -> stock > 0)
                .count() / 3.0;

        return product.getSalesUnits() * salesWeight + stockRatio * stockRatioWeight;
    }
}
