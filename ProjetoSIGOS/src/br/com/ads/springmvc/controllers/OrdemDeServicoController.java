package br.com.ads.springmvc.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ads.springmvc.models.AcaoTecnica;
import br.com.ads.springmvc.models.Equipamento;
import br.com.ads.springmvc.models.Orcamento;
import br.com.ads.springmvc.models.OrdemDeServico;
import br.com.ads.springmvc.models.Tecnico;
import br.com.ads.springmvc.models.Usuario;
import br.com.ads.springmvc.repositorios.RepositorioAcaoTecnica;
import br.com.ads.springmvc.repositorios.RepositorioCliente;
import br.com.ads.springmvc.repositorios.RepositorioEquipamento;
import br.com.ads.springmvc.repositorios.RepositorioOrcamento;
import br.com.ads.springmvc.repositorios.RepositorioOrdemDeServico;
import br.com.ads.springmvc.repositorios.RepositorioTecnico;
import br.com.ads.springmvc.repositorios.RepositorioUsuario;
import br.com.ads.springmvc.services.DescricaoAcaoTecnica;
import br.com.ads.springmvc.services.DescricaoStatusOrcamento;
import br.com.ads.springmvc.services.DescricaoStatusOs;
import br.com.ads.springmvc.services.RegistroDeAcaoTecnica;

@Controller
@RequestMapping("/ordem_de_servico")
public class OrdemDeServicoController {
	
	@Autowired
	RepositorioOrdemDeServico repositorioOrdemDeServico;
	
	@Autowired
	RepositorioTecnico repositorioTecnico;
	
	@Autowired
	RepositorioCliente repositorioCliente;
	
	@Autowired
	RepositorioEquipamento repositorioEquipamento;
	
	@Autowired
	RepositorioOrcamento repositorioOrcamento;
	
	@Autowired
	RepositorioAcaoTecnica repositorioAcaoTecnica;
	
	@Autowired
	RepositorioUsuario repositorioUsuario;
	
	@Autowired
	RegistroDeAcaoTecnica registroDeAcaoTecnica;
	
	@RequestMapping(value = "/lista_os", method = RequestMethod.GET)
	public String listar(Model model) {	
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Usuario usuario = repositorioUsuario.findByUsername(currentPrincipalName);
		
		if(usuario.getMatriculaTecnico() == null) {			
			List<OrdemDeServico> ordensServico = repositorioOrdemDeServico.findAll();
			
			//List<AcaoTecnica> acoesTecnias = repositorioAcaoTecnica.findAllByOS((long)2);
			//model.addAttribute("acoesTecnicas", acoesTecnias);
			model.addAttribute("ordensServico", ordensServico);
			return "ordemServico.listar.tiles";			
		}
		else {
			String matriculaTec = usuario.getMatriculaTecnico();
			Tecnico tecnico = repositorioTecnico.findByMatricula(matriculaTec);
			Long idTec = tecnico.getId();
			Set<OrdemDeServico> ordensServicoDoTec = repositorioOrdemDeServico.findAllOsTec(idTec);
			model.addAttribute("ordensServico", ordensServicoDoTec);
			return "ordemServico.listar.tiles";
		}
	}
	
	@RequestMapping(value = "/nova_os", method = RequestMethod.GET)
	public String adicionar(Model model) {			
		model.addAttribute("novaOS", new OrdemDeServico());
		return "ordemServico.adicionar.tiles";
	}
	
	@RequestMapping(value = "/nova_os", method = RequestMethod.POST)
	public String adicionar(@ModelAttribute("novaOS") @Valid OrdemDeServico novaOS,  BindingResult result, Model model) {
		
		novaOS.setDataAbertura(new Date());
		if(result.hasErrors()){	
			//System.out.println(result.toString());
			return "ordemServico.adicionar.tiles";			
		}
		Equipamento equipOs = repositorioEquipamento.findOne(novaOS.getEquipamento().getId());
		equipOs.setManutencao(true);
		repositorioEquipamento.save(equipOs);
		novaOS.setStatus(DescricaoStatusOs.NAO_INICIADA.getString());
		repositorioOrdemDeServico.save(novaOS);	
		Long id_os = novaOS.getId();
		model.addAttribute("sucesso", "sucesso");
		model.addAttribute("id_os", id_os);
		return "ordemServico.adicionar.tiles";
		//return "redirect:/ordem_de_servico/lista_os";		
	}
	
	@RequestMapping(value = "/associar_tecnico", method = RequestMethod.POST)
	public String associarTecnico (@ModelAttribute("iniciarOs") @Valid AcaoTecnica iniciarOs,  BindingResult result, Model model) {
		
		OrdemDeServico ordemDeServico = repositorioOrdemDeServico.findOne(iniciarOs.getOrdemDeServico().getId());
		Tecnico tecnico = repositorioTecnico.findOne(iniciarOs.getTecnico().getId());
		
		if(!ordemDeServico.getAcoesTecnicas().isEmpty()) {
			repositorioAcaoTecnica.updateTecnico(tecnico, ordemDeServico.getId());
			registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.ALTERACAO_TECNICO.getString() + tecnico.getNome(), ordemDeServico, tecnico);
			return "redirect:/ordem_de_servico/visualizar_os/" + ordemDeServico.getId();
		}
		ordemDeServico.setStatus(DescricaoStatusOs.EM_ANDAMENTO.getString());
		repositorioOrdemDeServico.save(ordemDeServico);
		registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.OS_INICIADA.getString() + tecnico.getNome(), ordemDeServico, tecnico);			
		return "redirect:/ordem_de_servico/lista_os";
	}
	
	@RequestMapping(value = "/visualizar_os/{id}", method = RequestMethod.GET)
	public String visualizar (@PathVariable("id") Long id, Model model) {
				
		OrdemDeServico ordemServico = repositorioOrdemDeServico.findOne(id);
		
		Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(ordemServico.getId());
		List<Equipamento> equipamentosCliente = repositorioEquipamento.findByIdEquip(ordemServico.getEquipamento().getCliente().getId());
		
		model.addAttribute("equipamentos", equipamentosCliente);
		model.addAttribute("tecnicoDaOs", tecnico);
		model.addAttribute("tecnicos", repositorioTecnico.findAll());
		model.addAttribute("ordemServico", ordemServico);
		model.addAttribute("iniciarOs", new AcaoTecnica());
		return "ordemServico.alterar.tiles";
	}
	
	@RequestMapping(value = "/alterar_os/{id}/{idEqp}", method = RequestMethod.POST)
	public String alterar (@PathVariable("id") Long id, @PathVariable("idEqp") Long idEqp, Model model) {
		
		Equipamento equipamento = repositorioEquipamento.findOne(idEqp);
		OrdemDeServico ordemServico = repositorioOrdemDeServico.findOne(id);
		Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(ordemServico.getId());
		ordemServico.setEquipamento(equipamento);
		registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.ALTERACAO_EQUIPAMENTO.getString(), ordemServico, tecnico);
		repositorioOrdemDeServico.save(ordemServico);
		
		return "redirect:/ordem_de_servico/visualizar_os/" + ordemServico.getId();
	}
	
	/*@RequestMapping(value = "/autoCompCliente", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Cliente> pesquisarPorNome (@RequestParam(name = "term", defaultValue= "") String nome){		
		List<Cliente> listaCliente = repositorioCliente.findByNome(nome);	
		return listaCliente;
	}*/	
	
	@RequestMapping(value = "/informacoesOS", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List <AcaoTecnica> getAcoesOS (@RequestParam(name = "id") Long id){
		List<AcaoTecnica> acoesTecnias = repositorioAcaoTecnica.findAllByOS(id);
		return acoesTecnias;
	}
	
	@RequestMapping(value = "/equipCliente", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List <Equipamento> pesquisaEquip (@RequestParam(name = "numSerie") String numSerie){
		List<Equipamento> equipamentos = repositorioEquipamento.findByNumSerie(numSerie);
		return equipamentos;
	}
	
	//Finalizar OS
	@RequestMapping(value = "/finalizar/{id}", method = RequestMethod.GET)
	public String aprovarOrcamento (@PathVariable("id") Long id,
									@RequestParam(name = "finalizar", required=false) Long finalizar) {
		
		OrdemDeServico os = repositorioOrdemDeServico.findOne(id);
		Orcamento orcamento = repositorioOrcamento.findOne(os.getOrcamento().getId());
		Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(orcamento.getOrdemDeServico().getId());
		Equipamento equipamento = os.getEquipamento();
		equipamento.setManutencao(false);
		repositorioEquipamento.save(equipamento);
		orcamento.setStatus(DescricaoStatusOrcamento.FATURAMENTO.getString());
		repositorioOrcamento.save(orcamento);
		os.setStatus(DescricaoStatusOs.FINALIZADA.getString());
		repositorioOrdemDeServico.save(os);
		registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.OS_FINALIZADA.getString(), orcamento.getOrdemDeServico(), tecnico);
		return "forward:/orcamento/lista_orcamento";
	}
	
	//Relatório ordem de Servico	

	 @RequestMapping(value = "/relatorio_analitico", method = RequestMethod.GET)
	 public String reportOrdemDeServico(Model model, @RequestParam(name = "format", defaultValue = "pdf", required = false)  String format,
	 						  @RequestParam(name = "num_os") Long id) {
		 
		List<AcaoTecnica> listAcao = repositorioAcaoTecnica.findAllByOS(id);
		OrdemDeServico os = repositorioOrdemDeServico.findOne(id);
		Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(os.getId());
		
		if(listAcao != null) {
			 model.addAttribute("format", format);
			 model.addAttribute("datasource", listAcao);				 							
			 model.addAttribute("id", os.getId());	
			 model.addAttribute("status", os.getStatus());	
			 model.addAttribute("abertura", os.getDataAbertura());
			 model.addAttribute("tecnico", tecnico.getNome());	
			 model.addAttribute("razao_social", os.getEquipamento().getCliente().getRazaoSocial());	
			 model.addAttribute("cnpj", os.getEquipamento().getCliente().getCnpj());	
			 model.addAttribute("email", os.getEquipamento().getCliente().getEnderecoContato().getEmail());	
			 model.addAttribute("fone", os.getEquipamento().getCliente().getEnderecoContato().getFone());	
			 model.addAttribute("tipo_modelo", os.getEquipamento().getTipoEquipamento().getDescricao() +" - "
					 			+  os.getEquipamento().getModeloEquipamento().getDescricao());
			 model.addAttribute("numero_serie", os.getEquipamento().getNumeroSerie());
			 
			 return "ordem_de_servico_report";
		}
		else {
			return null; // Criar redirect quando report null.
		}		
	 }
	 
	//Relatório termo de recebimento do equipamento	
	 
	 @RequestMapping(value = "/termo_de_receb_equip", method = RequestMethod.GET)
	 public String reportTermoRecebeEquip(Model model, @RequestParam(name = "format", defaultValue = "pdf", required = false)  String format,
	 						  @RequestParam(name = "num_os") Long id) {
		 
		//List<Tecnico> listAcao = repositorioAcaoTecnica.findAllByOS(id);
		OrdemDeServico os = repositorioOrdemDeServico.findOne(id);
		//Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(os.getId());
		List<String> list = new ArrayList<String>();
		list.add("termo");
		
			 model.addAttribute("format", format);
			 model.addAttribute("datasource", list);
			 model.addAttribute("id", os.getId());	
			 model.addAttribute("status", os.getStatus());	
			 model.addAttribute("abertura", os.getDataAbertura());
			 model.addAttribute("razao_social", os.getEquipamento().getCliente().getRazaoSocial());	
			 model.addAttribute("cnpj", os.getEquipamento().getCliente().getCnpj());	
			 model.addAttribute("email", os.getEquipamento().getCliente().getEnderecoContato().getEmail());	
			 model.addAttribute("fone", os.getEquipamento().getCliente().getEnderecoContato().getFone());	
			 model.addAttribute("tipo_modelo", os.getEquipamento().getTipoEquipamento().getDescricao() +" - "
					 			+  os.getEquipamento().getModeloEquipamento().getDescricao());
			 model.addAttribute("numero_serie", os.getEquipamento().getNumeroSerie());
			 return "termo_recebimento_de_equip_report";
	
	 }	
}
