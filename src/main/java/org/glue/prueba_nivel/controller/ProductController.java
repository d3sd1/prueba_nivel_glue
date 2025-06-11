package org.glue.prueba_nivel.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.glue.prueba_nivel.controller.dto.request.SortRequestDTO;
import org.glue.prueba_nivel.controller.dto.response.ProductResponseDTO;
import org.glue.prueba_nivel.mapper.ProductMapper;
import org.glue.prueba_nivel.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping("/sorted")
    public List<ProductResponseDTO> getSortedProducts(@RequestBody @Valid SortRequestDTO requestDTO) {
        return service.sortProducts(requestDTO.getSalesWeight(), requestDTO.getStockRatioWeight())
                .stream()
                .map(mapper::toProductResponseDto)
                .collect(Collectors.toList());
    }
}
