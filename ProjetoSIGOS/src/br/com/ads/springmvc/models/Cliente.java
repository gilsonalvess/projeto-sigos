package br.com.ads.springmvc.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/* @JsonManagedReference é a parte direta da referência - aquela que é serializada normalmente.
   @JsonBackReference é a parte traseira da referência - será omitido da serialização.*/

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "razao_social", nullable = false, length = 80)
	@NotNull(message = "Razão social é obrigatório")
	@Size(min = 5, max = 80, message = "A razão social deve conter entre 5 e 50 caracteres")
	private String razaoSocial;

	@Column(name = "nome_fantasia", nullable = false, length = 80)
	@NotNull(message = "Nome fantasia é obrigatório")
	@Size(min = 5, max = 80, message = "O nome fantasia deve conter entre 3 e 50 caracteres")
	private String nomeFantasia;

	@Column(name = "cnpj", nullable = false, length = 14)
	@NotNull(message = "CNPJ é obrigatório")
	@NotEmpty(message = "CNPJ é obrigatório")
	@Size(min = 14, max = 14)
	private String cnpj;

	@Column(name = "inscr_estadual", nullable = false, length = 11)
	private String inscrEstadual;

	@Column(name = "inscr_municipal", nullable = false, length = 11)
	private String inscrMunicipal;
	
	@JsonManagedReference
	@JoinColumn(name = "tipo_contrato_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = "*")
	private TipoContrato tipoContrato;
	
	@JsonManagedReference
	@JoinColumn(name = "endereco_id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private EnderecoContatoCliente enderecoContato;	
	
	@JsonBackReference
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // ver cascade orphanRemoval
	private Set<Equipamento> equipamento;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscrEstadual() {
		return inscrEstadual;
	}

	public void setInscrEstadual(String inscrEstadual) {
		this.inscrEstadual = inscrEstadual;
	}

	public String getInscrMunicipal() {
		return inscrMunicipal;
	}

	public void setInscrMunicipal(String inscrMunicipal) {
		this.inscrMunicipal = inscrMunicipal;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public EnderecoContatoCliente getEnderecoContato() {
		return enderecoContato;
	}

	public void setEnderecoContato(EnderecoContatoCliente enderecoContato) {
		this.enderecoContato = enderecoContato;
	}

	public Set<Equipamento> getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Set<Equipamento> equipamento) {
		this.equipamento = equipamento;
	}

}
