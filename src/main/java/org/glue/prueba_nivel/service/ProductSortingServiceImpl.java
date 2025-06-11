package org.glue.prueba_nivel.service;

import lombok.RequiredArgsConstructor;
import org.glue.prueba_nivel.database.entity.ProductEntity;
import org.glue.prueba_nivel.database.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSortingServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<ProductEntity> sortProducts(double salesWeight, double stockRatioWeight) {
        return productRepository.findAll().stream()
                .sorted((p1, p2) -> Double.compare(score(p2, salesWeight, stockRatioWeight), score(p1, salesWeight, stockRatioWeight)))
                .collect(Collectors.toList());
    }

    private double score(ProductEntity product, double salesWeight, double stockRatioWeight) {
        return 0;
    }
}
