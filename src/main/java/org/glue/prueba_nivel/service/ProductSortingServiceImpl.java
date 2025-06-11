package org.glue.prueba_nivel.service;

import lombok.RequiredArgsConstructor;
import org.glue.prueba_nivel.database.entity.ProductEntity;
import org.glue.prueba_nivel.database.repository.ProductRepository;
import org.glue.prueba_nivel.service.scoring.ScoringStrategy;
import org.glue.prueba_nivel.service.scoring.ScoringStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSortingServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<ProductEntity> sortProducts(double salesWeight, double stockRatioWeight) {
        ScoringStrategy strategy = ScoringStrategyFactory.create(salesWeight, stockRatioWeight);
        return productRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(p -> -strategy.score(p)))
                .collect(Collectors.toList());
    }
}
