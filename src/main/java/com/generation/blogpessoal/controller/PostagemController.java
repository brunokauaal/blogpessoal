package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController  //indicar que classe e controladora
@RequestMapping("/postagens") // endereço 
@CrossOrigin  (origins="*", allowedHeaders = "*") //Aceitamos qulquer origin e qualquer requisiçao tbm 
public class PostagemController {

	
	@Autowired	//Injeçao de dependencia apartir de agr tu Spring cria objetos manipula dados dleta e etc
	private  PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){   //resposta http status no caso  vai trazer lista de postagens que tem la dentro
		
		return ResponseEntity.ok(postagemRepository.findAll());
		
			//SELECT * FORM tb_postagens;	
	}
}
