package org.glue.prueba_nivel.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


import lombok.AllArgsConstructor;

@Document(collection = "products")
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;

    @Field("sales_units")
    private Integer salesUnits;

    private List<Stock> stocks;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Stock {
        private String size;

        @Field("units")
        private int units;
    }
}
