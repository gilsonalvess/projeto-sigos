package br.com.ads.springmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ads.springmvc.models.Servico;
import br.com.ads.springmvc.repositorios.RepositorioServico;

@Controller
@RequestMapping("/servico")
public class ServicoController {
	
	@Autowired
	RepositorioServico repositorioServico;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model){
		List<Servico> servico = repositorioServico.findAll();
		model.addAttribute("servicos", servico);
		return "servico.listar.tiles";
	}	
}

