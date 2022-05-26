package br.com.livraria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.livraria.entidades.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Integer>{

}
