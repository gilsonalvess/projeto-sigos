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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@SessionScope
@Table(name = "orcamento")
public class Orcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_criacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
	private Date dataCriacao;
	
	@JsonBackReference
	@OneToMany(mappedBy = "orcamento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ServicoOrcamento> servicoOrcamento;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "ordem_servico_id")
	private OrdemDeServico ordemDeServico;
	
	@JsonBackReference
	@JoinColumn(name = "tecnico_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = "*")
	private Tecnico tecnico;
	
	@Column(name= "valor_total")
	@NotNull(message = "*")
	@Min(value = 1, message="O valor do oramento nao pode ser igual a R$ 0.0. Favor informar os itens do serviço")
	private Double valorTotal = 0.0;
	
	@Column(name= "info_adicionais")
	private String infoAdicionais;
	
	@Column
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public OrdemDeServico getOrdemDeServico() {
		return ordemDeServico;
	}

	public Set<ServicoOrcamento> getServicoOrcamento() {
		return servicoOrcamento;
	}

	public void setServicoOrcamento(Set<ServicoOrcamento> servicoOrcamento) {
		this.servicoOrcamento = servicoOrcamento;
	}

	public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
		this.ordemDeServico = ordemDeServico;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getInfoAdicionais() {
		return infoAdicionais;
	}

	public void setInfoAdicionais(String infoAdicionais) {
		this.infoAdicionais = infoAdicionais;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}		
	
}
