package br.com.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.DTO.LivroDTO;
import br.com.livraria.Exception.LivroException;
import br.com.livraria.entidades.Livro;
import br.com.livraria.service.LivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	LivroService livroService;

	@GetMapping("/listar")
	public ResponseEntity<List<LivroDTO>> listarLivros() {
		return ResponseEntity.ok(livroService.listarLivro());
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Integer id) throws LivroException {
		return ResponseEntity.ok(livroService.buscarLivroPorId(id));
	}

	@PostMapping("/adicionar")
	public ResponseEntity<String> adicionar(@RequestBody LivroDTO livroDTO) {
		return ResponseEntity.ok(livroService.adicionarLivro(livroDTO));
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<String> atualizarUsandoPathVariable(@PathVariable Integer id, @RequestBody LivroDTO livroDTO)
			throws LivroException {
		return ResponseEntity.ok(livroService.atualizarLivro(id, livroDTO));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		livroService.deletarLivro(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
