package com.mfaias.fitnesspersonal.controller;

import com.mfaias.fitnesspersonal.dto.request.ProductRequestDTO;
import com.mfaias.fitnesspersonal.dto.response.ProductResponseDTO;
import com.mfaias.fitnesspersonal.entity.Product;
import com.mfaias.fitnesspersonal.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProductController implements BaseController{

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

        @PostMapping
        public ResponseEntity<ProductResponseDTO> cadastrar(@RequestBody @Valid ProductRequestDTO request){
                ProductResponseDTO productDto = productService.cadastrarProduto(request);
                return ResponseEntity.created(gerarUri(productDto.id())).body(productDto);
        }

}
