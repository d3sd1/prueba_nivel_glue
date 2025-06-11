package org.glue.prueba_nivel.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toDTO(Product product);
}