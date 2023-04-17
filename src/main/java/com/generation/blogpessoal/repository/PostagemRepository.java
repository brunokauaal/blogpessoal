package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.generation.blogpessoal.model.Postagem;
//Nome da interface                    //nome da class dps a chave 


public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

	// aqui so assinamos os metodos abstrato
	// lista de postagem que vai buscar a palavra titulo

	// Param indica que a variavel sera parametro da consuluta do tipo like
	// Containing - > like com %%
	// WHERE -> By
	// SELECT * FROM tb_postagens WHERE titulo LIKE "%titulo%"
}
