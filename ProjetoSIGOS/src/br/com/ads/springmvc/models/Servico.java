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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "servico")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column
	@NotNull
	private String descricao;
	
	@Column
	@NotNull(message = "*")
	private Double valor = 0.0;
	
	//@JsonBackReference
	@OneToMany(mappedBy = "servico", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ServicoOrcamento> servicoOrcamento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Set<ServicoOrcamento> getServicoOrcamento() {
		return servicoOrcamento;
	}

	public void setServicoOrcamento(Set<ServicoOrcamento> servicoOrcamento) {
		this.servicoOrcamento = servicoOrcamento;
	}
	
}
