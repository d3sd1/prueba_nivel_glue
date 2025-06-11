package org.glue.prueba_nivel.service.scoring;

import org.glue.prueba_nivel.database.entity.Product;

public interface ScoringStrategy {
    double score(Product product);
}