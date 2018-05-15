package br.com.ads.springmvc.models;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "ordem_de_servico")
public class OrdemDeServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	//@NotNull(message = "A data de criação é obrigatória")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
	private Date dataAbertura;

	@JsonManagedReference
	@JoinColumn(name = "equipamento_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)	
	@NotNull(message = "*")
	private Equipamento equipamento;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "ordemDeServico")
	private Orcamento orcamento;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "ordemDeServico", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<AcaoTecnica> acaoTecnica;
	
	@Column(nullable = true, length = 4096)
	private String status;
	
	@Column(nullable = true, length = 4096)
	private String observacoes;	
	
	@Column(name = "info_defeito", nullable = true, length = 4096)
	private String infoDefeito;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Set<AcaoTecnica> getAcoesTecnicas() {
		return acaoTecnica;
	}

	public void setAcoesTecnias(Set<AcaoTecnica> acaoTecnica) {
		this.acaoTecnica = acaoTecnica;
	}	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getInfoDefeito() {
		return infoDefeito;
	}

	public void setInfoDefeito(String infoDefeito) {
		this.infoDefeito = infoDefeito;
	}

	public void setAcoesTecnicas(Set<AcaoTecnica> acaoTecnica) {
		this.acaoTecnica = acaoTecnica;
	}

	public Set<AcaoTecnica> getAcaoTecnica() {
		return acaoTecnica;
	}

	public void setAcaoTecnica(Set<AcaoTecnica> acaoTecnica) {
		this.acaoTecnica = acaoTecnica;
	}		
	
}
