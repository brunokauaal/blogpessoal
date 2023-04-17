package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

@RestController // indicar que classe e controladora
@RequestMapping("/postagens") // endereço
@CrossOrigin(origins = "*", allowedHeaders = "*") // Aceitamos qulquer origin e qualquer requisiçao tbm
public class PostagemController {

	@Autowired // Injeçao de dependencia apartir de agr tu Spring cria objetos manipula dados
	// dleta e etc para ter acesso ao dados do db
	private PostagemRepository postagemRepository;

	@Autowired
	private TemaRepository TemaRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() { // resposta http status no caso vai trazer lista de postagens que
														// tem la dentro fizemos uma listagem da nossa tabela
		// vai devolver o status e o metodo que vai mostrar nossa tabela
		return ResponseEntity.ok(postagemRepository.findAll());

		// SELECT * FORM tb_postagens;
	}

	@GetMapping("/{id}") // indicando que o parametro e variavel ou seja valor altera conforme a
							// necessidade
	public ResponseEntity<Postagem> getById(@PathVariable Long id) { // serve pra puxar informaçoes que vem do id e o
																		// parametro do metodo
		// LAMBIDA
		return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)) // dentro da variavel
																							// resposta joga o resultado
																							// se for null ele devolve o
																							// or
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

		// SELECT * FORM tb_postagens WHERE id=?;
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {

		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));

		// SELECT * FROM tb_postagens WHERE titulo LIKE "%titulo%";
}
	
	//fazer postagem
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
	return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		
		
		/* INSERT  INTO tb_postagens (data,titulo,texto)
		Values(???)*/
	}
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid  @RequestBody Postagem postagem){
	
		if(postagemRepository.existsById(postagem.getId())) {
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();// Retorna status HTTP 404 se a postagem não existir
		}
			

	
		
		
		//fazer a validaçao de id
		/* UPDATE tb_postagens SET titulo= ?, texto = ?, data =? , WHERE id = id */
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
	 Optional <Postagem>postagem = postagemRepository.findById(id);
	
			if(postagem.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			else
				postagemRepository.deleteById(id);
			
		throw new ResponseStatusException(HttpStatus.OK);
		
		//Não sei se deixo status ok ou nocontent 
		
		/* DELETE * FROM tb_postagens WHERE  id= id*/
	}
	
	
	
    
	
	
}
