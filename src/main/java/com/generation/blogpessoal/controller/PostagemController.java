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

	private PostagemRepository postagemRepository;

	@Autowired
	private TemaRepository temaRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() { // resposta http status no caso vai trazer lista de postagens que
														// tem la dentro do repository fizemos uma listagem da nossa
														// tabela

		return ResponseEntity.ok(postagemRepository.findAll()); // vai devolver o status e o metodo que vai mostrar
																// nossa tabela

		// SELECT * FORM tb_postagens;
	}

	@GetMapping("/{id}") 
	public ResponseEntity<Postagem> getById(@PathVariable Long id) { 

		return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)) 
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

		// SELECT * FORM tb_postagens WHERE id=?;
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {

		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));

		// SELECT * FROM tb_postagens WHERE titulo LIKE "%titulo%";
	}

	// fazer postagem
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
		// INSERT INTO tb_postagens (titulo,texto) VALUES(???)

		return temaRepository.findById(postagem.getTema().getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {

		return postagemRepository.findById(postagem.getId())
				.map(resposta -> temaRepository.findById(postagem.getTema().getId())
						.map(resposta2 -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
						.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

		/* UPDATE tb_postagens SET titulo= ?, texto = ?, data =? , WHERE id = id */
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);

		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		else
			postagemRepository.deleteById(id);

		throw new ResponseStatusException(HttpStatus.NO_CONTENT);

		

		
	}

}
