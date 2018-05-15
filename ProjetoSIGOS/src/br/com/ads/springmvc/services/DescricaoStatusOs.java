package br.com.ads.springmvc.services;

public enum DescricaoStatusOs {
	
	NAO_INICIADA("Não iniciada"), //O.S. Sem técnico associado
	EM_ANDAMENTO("Em andamento"), //Equipamento com o tecnico
	ORCAMENTO("Orçamento"), //Orçamento gerado
	AGUARDANDO_PECAS("Aguardando peças"), 
	SERVICO_EM_EXECUCAO("Serviço em execução"),
	FINALIZADA("Finalizada");	
	
	private final String descricao;
	
	private DescricaoStatusOs(final String descricao) {
        this.descricao = descricao;
    }
	
	public String getString() {
	      return this.descricao;
	}


}
