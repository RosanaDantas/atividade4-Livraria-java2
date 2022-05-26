package br.com.livraria.entidades;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 5, max = 30)
	@NotNull
	private String titulo;

	@Size(min = 3, max = 20)
	@NotNull
	private String tipo;

	@Size(min = 10, max = 40)
	@NotNull
	private String autor;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataPublicacao;

	public Livro() {
	}

	public Livro(Integer id, @Size(min = 5, max = 30) @NotNull String titulo,
			@Size(min = 3, max = 20) @NotNull String tipo, @Size(min = 10, max = 40) @NotNull String autor,
			@Past LocalDateTime dataPublicacao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.tipo = tipo;
		this.autor = autor;
		this.dataPublicacao = dataPublicacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDateTime dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
}
