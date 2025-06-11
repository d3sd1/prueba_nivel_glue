package org.glue.prueba_nivel.service;

import org.glue.prueba_nivel.database.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> sortProducts(double salesWeight, double stockRatioWeight);
}