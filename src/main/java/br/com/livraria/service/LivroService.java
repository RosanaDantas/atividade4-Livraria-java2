package br.com.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.DTO.LivroDTO;
import br.com.livraria.Exception.LivroException;
import br.com.livraria.Repository.LivroRepository;
import br.com.livraria.entidades.Livro;

@Service
public class LivroService {
	
	@Autowired
	LivroRepository livroRepository;

	public List<LivroDTO> listarLivro() {
		List<Livro> listaLivro = livroRepository.findAll();
		List<LivroDTO> listaLivroDTO = new ArrayList<>();

		for (Livro livro : listaLivro) {
			LivroDTO livroDTO = new LivroDTO();
			transformarEntityEmDTO(livro, livroDTO);
			listaLivroDTO.add(livroDTO);
		}
		return listaLivroDTO;
	}

	public LivroDTO buscarLivroPorId(Integer id) throws LivroException {
		Optional<Livro> livro = livroRepository.findById(id);
		LivroDTO livroDTO = new LivroDTO();

		if (livro.isPresent()) {
			Livro livroNoBanco = livro.get();
			transformarEntityEmDTO(livroNoBanco, livroDTO);
			return livroDTO;
		}
		throw new LivroException("Não foi possível encontrar um livro com o id informado.");
	}

	public String adicionarLivro(LivroDTO livroDTO) {
		
		Livro livro = new Livro();
		transformarDTOEmEntity(livro, livroDTO);
		livroRepository.save(livro);
		
		return "O livro foi cadastrado com id: " + livro.getId();
	}

	public String atualizarLivro(Integer id, LivroDTO livroDTO) throws LivroException {
		
		Optional<Livro> livro = livroRepository.findById(id);
		Livro livroBanco = new Livro();
		if (livro.isPresent()) {
			livroBanco = livro.get();
			
			if (livroDTO.getAutor() != null) {
				livroBanco.setAutor(livroDTO.getAutor());
			}
			if (livroDTO.getDataPublicacao() != null) {
				livroBanco.setDataPublicacao(livroDTO.getDataPublicacao());
			}
			if (livroDTO.getTipo() != null) {
				livroBanco.setTipo(livroDTO.getTipo());
			}
			if (livroDTO.getTitulo() != null) {
				livroBanco.setTitulo(livroDTO.getTitulo());
			}
			livroRepository.save(livroBanco);
			return "O livro " + livroBanco.getTitulo() + " foi atualizado";
		}
		throw new LivroException("Não foi possível atualizar o livro");
	}

	public void deletarLivro(Integer id) {
		livroRepository.deleteById(id);
	}

	public LivroDTO transformarEntityEmDTO(Livro livro, LivroDTO livroDTO) {

		livroDTO.setAutor(livro.getAutor());
		livroDTO.setDataPublicacao(livro.getDataPublicacao());
		livroDTO.setTipo(livro.getTipo());
		livroDTO.setTitulo(livro.getTitulo());

		return livroDTO;
	}

	public Livro transformarDTOEmEntity(Livro livro, LivroDTO livroDTO) {

		livro.setAutor(livroDTO.getAutor());
		livro.setDataPublicacao(livroDTO.getDataPublicacao());
		livro.setTipo(livroDTO.getTipo());
		livro.setTitulo(livroDTO.getTitulo());

		return livro;
	}
}
