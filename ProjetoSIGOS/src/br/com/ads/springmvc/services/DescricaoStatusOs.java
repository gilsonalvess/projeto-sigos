package br.com.ads.springmvc.services;

public enum DescricaoStatusOs {
	
	NAO_INICIADA("N�o iniciada"), //O.S. Sem t�cnico associado
	EM_ANDAMENTO("Em andamento"), //Equipamento com o tecnico
	ORCAMENTO("Or�amento"), //Or�amento gerado
	AGUARDANDO_PECAS("Aguardando pe�as"), 
	SERVICO_EM_EXECUCAO("Servi�o em execu��o"),
	FINALIZADA("Finalizada");	
	
	private final String descricao;
	
	private DescricaoStatusOs(final String descricao) {
        this.descricao = descricao;
    }
	
	public String getString() {
	      return this.descricao;
	}


}
