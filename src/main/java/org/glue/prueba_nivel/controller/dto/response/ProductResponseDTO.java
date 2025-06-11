package org.glue.prueba_nivel.controller.dto.response;

import jakarta.persistence.*;
import lombok.Data;
import org.glue.prueba_nivel.database.entity.StockEntity;

import java.util.List;

@Data
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private Integer salesUnits;
    private List<StockResponseDTO> stocks;
}