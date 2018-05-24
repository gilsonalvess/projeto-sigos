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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "equipamento")
public class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	//Validar numero de série para ser único.
	@Column (name = "numero_serie")
	@NotNull(message = "*")
	private String numeroSerie;
	
	@JsonManagedReference
	@JoinColumn(name = "tipo_equipamento_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = "*")
	private TipoEquipamento tipoEquipamento;
	
	@JsonManagedReference
	@JoinColumn(name = "modelo_equipamento_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = "*")
	private ModeloEquipamento modeloEquipamento; 
	
	@JsonManagedReference
	@JoinColumn(name = "cliente_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = "*")
	private Cliente cliente;
	
	@JsonBackReference
	@OneToMany(mappedBy = "equipamento", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<OrdemDeServico> ordemDeServico;

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@NotNull(message = "A data de cadastro é obrigatória") // setar sempre a data do dia
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="UTC")
	private Date dataCadastro;
	
	@Column(nullable = false)	
	private Boolean manutencao; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public ModeloEquipamento getModeloEquipamento() {
		return modeloEquipamento;
	}

	public void setModeloEquipamento(ModeloEquipamento modeloEquipamento) {
		this.modeloEquipamento = modeloEquipamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<OrdemDeServico> getOrdemDeServico() {
		return ordemDeServico;
	}

	public void setOrdemDeServico(Set<OrdemDeServico> ordemDeServico) {
		this.ordemDeServico = ordemDeServico;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean isManutencao() {
		return manutencao;
	}

	public void setManutencao(Boolean manutencao) {
		this.manutencao = manutencao;
	}

}
