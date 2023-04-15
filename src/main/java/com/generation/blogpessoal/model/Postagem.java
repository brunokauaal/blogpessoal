package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//vamos definir o modelo de dados
@Entity // indicando para gerar tabela no banco de dados
@Table(name = "tb_postagens") // sao equivalentes a esse comando no mysql CREATE TABLE tb_postagens
public class Postagem {

	// criar atributos

	@Id                  //indica que o atributo id sera a  Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //estamos dizendo para o mysql que a responsa do auto increment e deles  e indicando que o id e o primary key
	private Long id; // long pq e objeto e podemos ter n postagens

	@NotBlank(message= "O Atributo  título é obrigatório")  //exclusivo para string texto  nao aceito nulo e  espaço vazio tipo no null porem so pra texto
	@Size(min= 5 ,max =100, message = "O atributo título deve ter no minimo 05  e no maximo 100 caracteres!")
	private String titulo;

	@NotBlank(message= "O Atributo  texto é obrigatório") 
	@Size(min= 10 ,max =1000, message = "O atributo texto deve ter no minimo 10  e no maximo 1000 caracteres!")
	private String texto;
	
	
	@UpdateTimestamp   // toda vez que atualizar ele mostra  a data e hr q foi feita alteraçao 
	private LocalDateTime data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

}
