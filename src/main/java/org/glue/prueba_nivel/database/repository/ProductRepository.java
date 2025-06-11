package org.glue.prueba_nivel.database.repository;

import org.glue.prueba_nivel.database.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface ProductRepository extends MongoRepository<Product, BigInteger> {

}
