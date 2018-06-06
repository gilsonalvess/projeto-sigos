package br.com.ads.springmvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ads.springmvc.models.Cliente;
import br.com.ads.springmvc.models.Equipamento;
import br.com.ads.springmvc.repositorios.RepositorioCliente;
import br.com.ads.springmvc.repositorios.RepositorioEnderecoContatoCliente;
import br.com.ads.springmvc.repositorios.RepositorioTipoContrato;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	RepositorioCliente repositorioCliente;
	
	@Autowired
	RepositorioTipoContrato repositorioTipoContrato;
	
	@Autowired
	RepositorioEnderecoContatoCliente repositorioEnderecoCliente;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Cliente> clientes = repositorioCliente.findAll();
		model.addAttribute("clientes", clientes);
		return "cliente.listar.tiles";
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String adicionar (Model model) {	
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("tpcontratos", repositorioTipoContrato.findAll()); 
		return "cliente.adicionar.tiles";
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(@ModelAttribute("cliente") @Valid Cliente novoCliente, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("tpcontratos", repositorioTipoContrato.findAll());
			return "cliente.adicionar.tiles";
		}	
		repositorioCliente.save(novoCliente);	
		return "redirect:/cliente/listar";
	}
	
	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET )
	public String alterar(@PathVariable("id") Long id, Model model){
		Cliente clienteASerAlterada = repositorioCliente.findOne(id);
		model.addAttribute("cliente", clienteASerAlterada);
		model.addAttribute("tpcontratos", repositorioTipoContrato.findAll());
		return "cliente.alterar.tiles";
	}
	
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public String alterar(@ModelAttribute("cliente") @Valid Cliente cliente, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("tpcontratos", repositorioTipoContrato.findAll());
			return "cliente.alterar.tiles";
		}
		repositorioCliente.save(cliente);
		return "redirect:/cliente/listar";
	}
	
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable("id") Long id, Model model){
		Cliente cliente = repositorioCliente.findOne(id);
		cliente.setTipoContrato(null);
		try {
			repositorioCliente.delete(cliente);
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("erro", e);
			return "erros.erro.tiles";			
		}		
		return "redirect:/cliente/listar";
	}
	
}
