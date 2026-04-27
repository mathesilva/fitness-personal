package com.mfaias.fitnesspersonal.controller;

import com.mfaias.fitnesspersonal.dto.request.ProductRequestDTO;
import com.mfaias.fitnesspersonal.dto.response.ProductResponseDTO;
import com.mfaias.fitnesspersonal.entity.Product;
import com.mfaias.fitnesspersonal.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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


        @GetMapping
        public ResponseEntity<List<ProductResponseDTO>> listarTodos(){
            List<ProductResponseDTO> productDto = productService.AllList();
            return ResponseEntity.ok(productDto);
        }

        @GetMapping("/busca")
        public ResponseEntity<List<ProductResponseDTO>> listarPorNome(@RequestParam String nome){
            return ResponseEntity.ok(productService.listNome(nome));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable  UUID id, @RequestBody ProductRequestDTO request){
            ProductResponseDTO productAtt = productService.attProduct(id, request);
            return ResponseEntity.ok(productAtt);
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<Product> deletar(@PathVariable UUID id){
            productService.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

}
