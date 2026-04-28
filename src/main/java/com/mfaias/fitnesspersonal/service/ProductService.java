package com.mfaias.fitnesspersonal.service;

import com.mfaias.fitnesspersonal.dto.request.ProductRequestDTO;
import com.mfaias.fitnesspersonal.dto.response.ProductResponseDTO;
import com.mfaias.fitnesspersonal.entity.Product;
import com.mfaias.fitnesspersonal.exceptions.ProdutoJaCadastrado;
import com.mfaias.fitnesspersonal.exceptions.ProdutoNaoEncontrado;
import com.mfaias.fitnesspersonal.repository.ProductRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO cadastrarProduto(@RequestBody ProductRequestDTO request) {

        Optional<Product> optProduct = productRepository.findByNome(request.nome());

        if (optProduct.isPresent()) {
            throw new ProdutoJaCadastrado("Produto já existe");
        }

        Product product = new Product();

        product.setNome(request.nome());
        product.setValor(request.valor());
        product.setQuantidadeEstoque(request.quantidadeEstoque());

        Product produtoSalvo = productRepository.save(product);


        return new ProductResponseDTO(produtoSalvo);
    }


    public List<ProductResponseDTO> AllList(){
         List<Product> listAll = productRepository.findAll();
         return listAll.stream().map(ProductResponseDTO::new).toList();
    }


    public List<ProductResponseDTO> listNome(@NotBlank(message = "O nome do produto é obrigatório") String nome){
        List<Product> listName = productRepository.findByNomeContainingIgnoreCase(nome);
        if (nome.isEmpty()){
            System.out.println("Este produto nao foi registrado ou nao existe");
        }
        return listName.stream().map(p -> new ProductResponseDTO(p)).toList();
    }


    public ProductResponseDTO attProduct(UUID uuid, ProductRequestDTO request){
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProdutoNaoEncontrado("Produto nao encontrado"));
        product.setNome(request.nome());
        product.setValor(request.valor());
        product.setQuantidadeEstoque(request.quantidadeEstoque());

        Product productSave = productRepository.save(product);
        return new ProductResponseDTO(productSave);
    }


    public void deletar(UUID id){
        if (!productRepository.existsById(id)){
            throw new ProdutoNaoEncontrado("Produto não encontrado");
        }
            productRepository.deleteById(id);
    }

}

