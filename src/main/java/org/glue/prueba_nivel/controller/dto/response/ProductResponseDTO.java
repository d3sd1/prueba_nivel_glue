package org.glue.prueba_nivel.controller.dto.response;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductResponseDTO {
    private int id;
    private String name;
    private BigInteger salesUnits;
    private Map<String, Integer> stocks;
}