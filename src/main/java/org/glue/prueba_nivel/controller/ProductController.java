package org.glue.prueba_nivel.controller;


import lombok.RequiredArgsConstructor;
import org.glue.prueba_nivel.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping("/sorted")
    public List<ProductResponseDTO> getSortedProducts(@RequestBody SortRequestDTO requestDTO) {
        return service.sortProducts(requestDTO.getSalesWeight(), requestDTO.getStockRatioWeight())
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
