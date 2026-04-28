package com.mfaias.fitnesspersonal.repository;

import com.mfaias.fitnesspersonal.entity.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    List<Product> findByNomeContainingIgnoreCase (String nome);

    Optional<Product> findByNome(@NotBlank(message = "O nome do produto é obrigatório") String nome);

    Optional<Product> findById(UUID uuid);
}
