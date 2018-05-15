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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tipo_equipamento")
public class TipoEquipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	@NotNull
	private String descricao;
	
	@JsonBackReference
	@OneToMany(mappedBy = "tipoEquipamento", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Equipamento> equipamento;

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

	public Set<Equipamento> getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Set<Equipamento> equipamento) {
		this.equipamento = equipamento;
	}

}
