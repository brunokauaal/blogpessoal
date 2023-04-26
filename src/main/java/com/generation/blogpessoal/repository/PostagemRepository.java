package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.generation.blogpessoal.model.Postagem;
//Nome da interface                    //nome da class dps a chave 


public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	//SELECT * FROM tb_postagens WHERE Titulo LIKE "%%"
	
	List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

	
}
