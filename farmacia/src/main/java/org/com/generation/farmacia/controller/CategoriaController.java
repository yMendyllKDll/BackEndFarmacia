package org.com.generation.farmacia.controller;

import java.util.List;
import org.com.generation.farmacia.model.Categoria;
import org.com.generation.farmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	@CrossOrigin(origins="*",allowedHeaders="*")
	@RequestMapping("/categoria")
	
	public class CategoriaController {
		
		@Autowired
		private CategoriaRepository repository;
		
		@GetMapping
		public ResponseEntity<List<Categoria>>getAll(){                       //List lista de tudo independente se tem ou não algo
			return ResponseEntity.ok(repository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Categoria> getById(@PathVariable Long id){
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp)) //"map(resp ->)" Serve como para achar ou não
					.orElse(ResponseEntity.notFound().build());
		}
		
		@GetMapping("/nome/{nome}") //Descrição 
		public ResponseEntity<List<Categoria>> getByName(@PathVariable String descricao){
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
		}
		
		@PostMapping
		public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
		}
		
		@PutMapping
		public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
			return ResponseEntity.ok(repository.save(categoria));
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long id) {
			repository.deleteById(id);
		}

}
