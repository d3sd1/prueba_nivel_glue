package org.glue.prueba_nivel.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductEntity {

    @Id
    private Integer id;

    private String name;

    @Column(name = "sales_units")
    private Integer salesUnits;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StockEntity> stocks;
}