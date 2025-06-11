package org.glue.prueba_nivel.controller.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SortRequestDTO {
    @NotNull(message = "El peso de ventas es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El peso de ventas debe ser mayor a 0")
    @DecimalMax(value = "1.0", inclusive = true, message = "El peso de ventas no puede ser mayor a 1")
    private Double salesWeight;

    @NotNull(message = "El peso de stock es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El peso de stock debe ser mayor a 0")
    @DecimalMax(value = "1.0", inclusive = true, message = "El peso de stock no puede ser mayor a 1")
    private Double stockRatioWeight;
}
