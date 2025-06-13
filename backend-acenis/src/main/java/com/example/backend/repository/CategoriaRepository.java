package com.example.backend.repository;

import com.example.backend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interface nos dá todos os métodos básicos de CRUD para a entidade Categoria.
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}