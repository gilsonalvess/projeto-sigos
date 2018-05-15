package br.com.ads.springmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ads.springmvc.models.Tecnico;
import br.com.ads.springmvc.repositorios.RepositorioAcaoTecnica;
import br.com.ads.springmvc.repositorios.RepositorioOrdemDeServico;
import br.com.ads.springmvc.repositorios.RepositorioTecnico;

@Controller
@RequestMapping("/tecnico")
public class TecnicoController {
	
	@Autowired
	RepositorioTecnico repositorioTecnico;
	
	@Autowired
	RepositorioAcaoTecnica repositorioAcaoTecnica;
	
	@Autowired
	RepositorioOrdemDeServico repositorioOrdemDeServico;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model){
		List<Tecnico> tecnico = repositorioTecnico.findAll();
		model.addAttribute("tecnicos", tecnico);
		return "tecnico.listar.tiles";
	}	
		
	@RequestMapping(value = "/listTecnicos", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List <Tecnico> tecnicos (){
		List<Tecnico> tecnicos = repositorioTecnico.findAll(); 
		return tecnicos;
	}
	
}
