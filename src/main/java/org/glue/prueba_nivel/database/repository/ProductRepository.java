package org.glue.prueba_nivel.database.repository;

import org.glue.prueba_nivel.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProductRepository extends JpaRepository<ProductEntity, BigInteger> {

}
