package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;
                 //Nome da interface                    //nome da class dps a chave 

@Repository  //eliminar assim que o guilherme arrumar a validaçao
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

}
