package br.com.gft.projetoapi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	@NotBlank(message="Campo texto é de preenchimento obrigatório.")
	@Size(max=1500, message="O campo comentário não pode conter mais de 1500 caracteres.")
	@JsonProperty("comentario") //muda o nome da propriedade no Json
	private String texto;
	
	@JsonInclude(Include.NON_NULL)

	private String usuario;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="dd/MM/yyyy")

	private Date data;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LIVRO_ID")
	@JsonIgnore  //quando o Jackson cria o json, ele vai criar o objeto livro e vai tentar colocar o objeto comentario dentro desse livro e, como o objeto comentario também tem livro, inicia-se uma relação cíclica infinita, portanto é necessário esse comentário.  
	private Livro livro;
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
