package org.glue.prueba_nivel.configuration;

import org.glue.prueba_nivel.database.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MongoDataInitializer {
    Logger logger = LoggerFactory.getLogger(MongoDataInitializer.class);

    @Bean
    CommandLineRunner initData(org.glue.prueba_nivel.database.repository.ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.saveAll(List.of(
                        new Product("1", "V-NECK BASIC SHIRT", 100, List.of(
                                new Product.Stock("S", 4),
                                new Product.Stock("M", 9),
                                new Product.Stock("L", 0)
                        )),
                        new Product("2", "CONTRASTING FABRIC T-SHIRT", 50, List.of(
                                new Product.Stock("S", 35),
                                new Product.Stock("M", 9),
                                new Product.Stock("L", 9)
                        )),
                        new Product("3", "RAISED PRINT T-SHIRT", 80, List.of(
                                new Product.Stock("S", 20),
                                new Product.Stock("M", 2),
                                new Product.Stock("L", 20)
                        )),
                        new Product("4", "PLEATED T-SHIRT", 3, List.of(
                                new Product.Stock("S", 25),
                                new Product.Stock("M", 30),
                                new Product.Stock("L", 10)
                        )),
                        new Product("5", "CONTRASTING LACE T-SHIRT", 650, List.of(
                                new Product.Stock("S", 0),
                                new Product.Stock("M", 1),
                                new Product.Stock("L", 0)
                        )),
                        new Product("6", "SLOGAN T-SHIRT", 20, List.of(
                                new Product.Stock("S", 9),
                                new Product.Stock("M", 2),
                                new Product.Stock("L", 5)
                        ))
                ));
                this.logger.info("✅ Productos inicializados en MongoDB");
            }
        };
    }
}
