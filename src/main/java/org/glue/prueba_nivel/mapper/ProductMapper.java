package org.glue.prueba_nivel.mapper;

import org.glue.prueba_nivel.controller.dto.response.ProductResponseDTO;
import org.glue.prueba_nivel.database.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toProductResponseDto(Product product);
}