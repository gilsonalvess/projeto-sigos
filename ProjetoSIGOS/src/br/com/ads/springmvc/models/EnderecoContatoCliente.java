package br.com.ads.springmvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "endereco_cliente")
public class EnderecoContatoCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(nullable = false, length = 30)
	private String logradouro;

	@Column(nullable = false, length = 10)
	@NotNull(message = "*")
	@NotEmpty(message = "*")
	private String numero;

	@Column(nullable = false, length = 30)
	private String complemento;

	@Column(nullable = false, length = 8)
	@NotNull(message = "*")
	private String cep;

	@Column(nullable = false, length = 40)
	@NotNull
	@NotEmpty
	private String bairro;

	@Column(nullable = false, length = 50)
	@NotNull
	@NotEmpty
	private String cidade;

	@Column(nullable = false, length = 20)
	@NotNull
	@NotEmpty
	private String uf;

	@Column(nullable = false, length = 20)
	@NotNull
	private String fone;

	@Column(nullable = false, length = 60)
	@NotNull
	private String email;

	@Column(name = "nome_contato", nullable = false, length = 30)
	@NotNull
	private String nomeContato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

}
