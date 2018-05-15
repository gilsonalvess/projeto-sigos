package br.com.ads.springmvc.services;

public enum DescricaoAcaoTecnica {
	OS_INICIADA("O.S. Iniciada - T�cnico do servi�o: "),
	OS_FINALIZADA("O.S. Finalizada"),	
	ALTERACAO_EQUIPAMENTO("O equipamento foi alterado"), 
	ALTERACAO_TECNICO("T�cnico do servi�o foi alterado para: "),
	ORCAMENTO_GERADO("Or�amento gerado"),
	ORCAMENTO_ENVIADO("Or�amento enviado - Aguardando aprova��o"),
	ORCAMENTO_EDITADO("Or�amento editado"),
	ORCAMENTO_APROVADO("Or�amento aprovado"),	
	ORCAMENTO_REPROVADO("Or�amento reprovado - motivo: "),
	ORCAMENTO_EXCLUIDO("O�amento exclu�do");
	
	private final String descricao;
	
	private DescricaoAcaoTecnica(final String descricao) {
        this.descricao = descricao;
    }
	
	public String getString() {
	      return this.descricao;
	}
}
