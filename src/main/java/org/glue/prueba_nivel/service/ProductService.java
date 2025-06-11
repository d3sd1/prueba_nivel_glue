package org.glue.prueba_nivel.service;

import org.glue.prueba_nivel.database.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> sortProducts(double salesWeight, double stockRatioWeight);
}