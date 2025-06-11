package org.glue.prueba_nivel.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Product {
    private final int id;
    private final String name;
    private final int salesUnits;
    private final Map<String, Integer> stocks;
}
