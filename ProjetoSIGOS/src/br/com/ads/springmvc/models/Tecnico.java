package br.com.ads.springmvc.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "tecnico")
public class Tecnico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull()
	private String matricula;

	@Column
	@NotNull
	private String nome;

	@Column
	private String telefone;	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tecnico", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<AcaoTecnica> acaoTecnica;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tecnico", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Orcamento> orcamento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<AcaoTecnica> getAcoesTecnicas() {
		return acaoTecnica;
	}

	public void setAcoesTecnicas(Set<AcaoTecnica> acaoTecnica) {
		this.acaoTecnica = acaoTecnica;
	}

	public Set<Orcamento> getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Set<Orcamento> orcamento) {
		this.orcamento = orcamento;
	}

	public Set<AcaoTecnica> getAcaoTecnica() {
		return acaoTecnica;
	}

	public void setAcaoTecnica(Set<AcaoTecnica> acaoTecnica) {
		this.acaoTecnica = acaoTecnica;
	}
	
}
