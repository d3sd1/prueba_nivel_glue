package org.glue.prueba_nivel.controller.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private Integer salesUnits;
    private List<StockResponseDTO> stocks;
}