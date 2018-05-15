package br.com.ads.springmvc.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ads.springmvc.models.Equipamento;
import br.com.ads.springmvc.repositorios.RepositorioCliente;
import br.com.ads.springmvc.repositorios.RepositorioEquipamento;
import br.com.ads.springmvc.repositorios.RepositorioModeloEquipamento;
import br.com.ads.springmvc.repositorios.RepositorioTipoEquipamento;

@Controller
@RequestMapping("/equipamento")
public class EquipamentoController {
	
	@Autowired
	private RepositorioEquipamento repositorioEquipamento;
	
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	@Autowired
	private RepositorioModeloEquipamento repositorioModeloEquipamento;
	
	@Autowired
	private RepositorioTipoEquipamento repositorioTipoEquipamento;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model){
		List<Equipamento> equipamento = repositorioEquipamento.findAll();
		model.addAttribute("equipamentos", equipamento);
		return "equipamento.listar.tiles";
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String adicionar(Model model){
		model.addAttribute("equipamento", new Equipamento());
		model.addAttribute("tpEquipamento", repositorioTipoEquipamento.findAll());
		model.addAttribute("mdEquipamento", repositorioModeloEquipamento.findAll());
		model.addAttribute("cliente", repositorioCliente.findAll());
		return "equipamento.adicionar.tiles";
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(Model model, @ModelAttribute("equipamento") @Valid Equipamento equipamento, BindingResult result){
		if(result.hasErrors()){		
			model.addAttribute("equipamento", new Equipamento());
			return "equipamento.adicionar.tiles";			
		}	
		equipamento.setDataCadastro(new Date());
		//equipamento.setManutencao(false);
		repositorioEquipamento.save(equipamento);
		return "redirect:/equipamento/listar";
	}
}
