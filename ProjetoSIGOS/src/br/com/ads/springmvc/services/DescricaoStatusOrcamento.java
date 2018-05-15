package br.com.ads.springmvc.services;

public enum DescricaoStatusOrcamento {
	
	NAO_ENVIADO("N�o enviado"),
	ENVIADO("Enviado"), 
	APROVADO("Aprovado"),
	FATURAMENTO("Faturamento"),
	REPROVADO("Reprovado");
	
	private final String descricao;
	
	private DescricaoStatusOrcamento(final String descricao) {
        this.descricao = descricao;
    }
	
	public String getString() {
	      return this.descricao;
	}

}
