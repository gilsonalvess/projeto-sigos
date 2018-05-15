package br.com.ads.springmvc.services;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ads.springmvc.models.Servico;
import br.com.ads.springmvc.models.ServicoOrcamento;
import br.com.ads.springmvc.repositorios.RepositorioServico;

@Service
public class AssistenteOrcamento {
	
	@Autowired
	RepositorioServico repositorioServico;
	
	public Set<ServicoOrcamento> addServico (Long id, Set<ServicoOrcamento> itensServico){
		
		Servico servico = repositorioServico.findOne(id);
		ServicoOrcamento servOrc = new ServicoOrcamento();				
		servOrc.setServico(servico);
		servOrc.setQuantidade(1);
		servOrc.setSubTotal(servico.getValor() * servOrc.getQuantidade());		
		itensServico.add(servOrc);
		return 	itensServico;
	}
	
	public Set<ServicoOrcamento> removeServico (Long id, Set<ServicoOrcamento> itensServico){
		
		ServicoOrcamento so = new ServicoOrcamento();
		Iterator<ServicoOrcamento> item = itensServico.iterator();
		
		while(item.hasNext()) {
			so = item.next();
			if(so.getServico().getId() == id) {
				item.remove();
			}
		}
		return 	itensServico;	
	}
	
	public Double somaTotal(List<ServicoOrcamento> listaItens) {
			
		int tam = listaItens.size();
		Double soma = 0.0;
		for(int i = 0; i < tam; i++) {
			soma+= listaItens.get(i).getSubTotal();
		}
		return soma;
	}	
	
	public Double somaTotalSet(Set<ServicoOrcamento> listaItens) {
		
		Double soma = 0.0;
		Iterator<ServicoOrcamento> item = listaItens.iterator();
		
		while(item.hasNext()) {
			soma+= item.next().getSubTotal();
		}
		return soma;
	}
}
