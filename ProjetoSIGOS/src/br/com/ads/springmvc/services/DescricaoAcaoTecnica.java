package br.com.ads.springmvc.services;

public enum DescricaoAcaoTecnica {
	OS_INICIADA("O.S. Iniciada - Técnico do serviço: "),
	OS_FINALIZADA("O.S. Finalizada"),	
	ALTERACAO_EQUIPAMENTO("O equipamento foi alterado"), 
	ALTERACAO_TECNICO("Técnico do serviço foi alterado para: "),
	ORCAMENTO_GERADO("Orçamento gerado"),
	ORCAMENTO_ENVIADO("Orçamento enviado - Aguardando aprovação"),
	ORCAMENTO_EDITADO("Orçamento editado"),
	ORCAMENTO_APROVADO("Orçamento aprovado"),	
	ORCAMENTO_REPROVADO("Orçamento reprovado - motivo: "),
	ORCAMENTO_EXCLUIDO("Oçamento excluído");
	
	private final String descricao;
	
	private DescricaoAcaoTecnica(final String descricao) {
        this.descricao = descricao;
    }
	
	public String getString() {
	      return this.descricao;
	}
}
