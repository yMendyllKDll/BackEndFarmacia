package org.com.generation.farmacia.repository;

import java.util.List;

import org.com.generation.farmacia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
		public List<Categoria>findAllByDescricaoContainingIgnoreCase(String descricao);
}
