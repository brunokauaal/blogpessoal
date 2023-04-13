package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;
                 //Nome da interface                    //nome da class dps a chave 
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

}
