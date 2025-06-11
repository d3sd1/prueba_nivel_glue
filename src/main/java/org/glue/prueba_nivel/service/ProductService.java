package org.glue.prueba_nivel.service;

import java.util.List;

public interface ProductService {
    List<Product> sortProducts(double salesWeight, double stockRatioWeight);
}