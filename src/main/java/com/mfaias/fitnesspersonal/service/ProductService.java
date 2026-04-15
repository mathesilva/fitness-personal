package com.mfaias.fitnesspersonal.service;

import com.mfaias.fitnesspersonal.dto.request.ProductRequestDTO;
import com.mfaias.fitnesspersonal.dto.response.ProductResponseDTO;
import com.mfaias.fitnesspersonal.entity.Product;
import com.mfaias.fitnesspersonal.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO cadastrarProduto(ProductRequestDTO request) {

        Optional<Product> optProduct = productRepository.findByNome(request.nome());

        if (optProduct.isPresent()) {
            throw new RuntimeException("Produto já existe");
        }

        Product product = new Product();

        product.setNome(request.nome());
        product.setValor(request.valor());
        product.setQuantidadeEstoque(request.quantidadeEstoque());

        Product produtoSalvo = productRepository.save(product);


        return new ProductResponseDTO(produtoSalvo);
    }
}

