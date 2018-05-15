package br.com.ads.springmvc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.ads.springmvc.models.AcaoTecnica;
import br.com.ads.springmvc.models.OrdemDeServico;
import br.com.ads.springmvc.models.Tecnico;
import br.com.ads.springmvc.repositorios.RepositorioAcaoTecnica;


@Service
@Component
public class RegistroDeAcaoTecnica {
	
	@Autowired
	RepositorioAcaoTecnica repositorioAcaoTecnica;
		
	public void registrarAcao(String descricao, OrdemDeServico ordemServico, Tecnico tecnico) {
		
		AcaoTecnica acaoTecnica = new AcaoTecnica();
		acaoTecnica.setDataAcao(new Date());
		acaoTecnica.setDescricao(descricao);
		acaoTecnica.setOrdemDeServico(ordemServico);
		acaoTecnica.setTecnico(tecnico);
		repositorioAcaoTecnica.save(acaoTecnica);
	}
}
