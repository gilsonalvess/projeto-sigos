package br.com.ads.springmvc.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import br.com.ads.springmvc.models.Orcamento;
import br.com.ads.springmvc.models.OrdemDeServico;
import br.com.ads.springmvc.models.Servico;
import br.com.ads.springmvc.models.ServicoOrcamento;
import br.com.ads.springmvc.models.Tecnico;
import br.com.ads.springmvc.repositorios.RepositorioAcaoTecnica;
import br.com.ads.springmvc.repositorios.RepositorioOrcamento;
import br.com.ads.springmvc.repositorios.RepositorioOrdemDeServico;
import br.com.ads.springmvc.repositorios.RepositorioServico;
import br.com.ads.springmvc.repositorios.RepositorioServicoOrcamento;
import br.com.ads.springmvc.services.AssistenteOrcamento;
import br.com.ads.springmvc.services.DescricaoAcaoTecnica;
import br.com.ads.springmvc.services.DescricaoStatusOrcamento;
import br.com.ads.springmvc.services.DescricaoStatusOs;
import br.com.ads.springmvc.services.Mailer;
import br.com.ads.springmvc.services.Mensagem;
import br.com.ads.springmvc.services.RegistroDeAcaoTecnica;

@Controller
@SessionScope
@RequestMapping("/orcamento")
public class OrcamentoController {
	
	private Object listaDeItens = null;
	private Object listaDeItensDoOrc = null;
	private Set<ServicoOrcamento> itensDoOrcamento = null;
	private List<ServicoOrcamento> itensServico = null;
	private Long identificadorOS = null;
	private Long identificadorOrc = null;	
		
	@Autowired
	RepositorioOrdemDeServico repositorioOrdemDeServico;
	
	@Autowired
	RepositorioServicoOrcamento repositorioServicoOrcamento;
	
	@Autowired
	RepositorioServico repositorioServico;
	
	@Autowired
	RepositorioOrcamento repositorioOrcamento;
	
	@Autowired
	RepositorioAcaoTecnica repositorioAcaoTecnica;
	
	@Autowired
	RegistroDeAcaoTecnica registroDeAcaoTecnica;
	
	@Autowired
	AssistenteOrcamento assistenteOrcamento;
	
	@Autowired
	Mailer mailer;
	
	@RequestMapping(value = "/lista_orcamento", method = RequestMethod.GET)
	public String listar(Model model) {	
		List<Orcamento> orcamento = repositorioOrcamento.findAll();
		//orcamento.get(1).getOrdemDeServico().getEquipamento().
		model.addAttribute("orcamentos", orcamento);
		return "orcamento.listar.tiles";
	}
	
	@RequestMapping(value = "/novo_orcamento/{idOs}", method = RequestMethod.GET)
	public String adicionar ( Model model, @PathVariable("idOs") Long idOs, @RequestParam(name = "servicoItem", required=false) Long servicoItem,
							  @RequestParam(name = "removeItem", required=false) Long removeItem) {		
		
		OrdemDeServico ordemServico = repositorioOrdemDeServico.findOne(idOs);
		Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(ordemServico.getId());
		
		if(listaDeItens == null || identificadorOS != idOs) {
			itensServico =  new ArrayList<ServicoOrcamento>();
			listaDeItens = null;
			identificadorOS = null;
			identificadorOS = idOs;			
		}		
		model.addAttribute("novoOrcamento", new Orcamento());
		model.addAttribute("ordemServico", ordemServico);
		model.addAttribute("tecnicoDaOs", tecnico);
		if(servicoItem != null) {
			listaDeItens = addServico(servicoItem);	
		}
		if(removeItem != null) {
			listaDeItens = removeServico(removeItem);
		}
		model.addAttribute("itens", listaDeItens);	
		model.addAttribute("soma", assistenteOrcamento.somaTotal(itensServico));
		model.addAttribute("servicos", repositorioServico.findAll());
		
		return "orcamento.adicionar.tiles";
		 
		//Implementar açao técnica para criação do orcamento da OS
	}
	
	@RequestMapping(value = "/novo_orcamento/{idOs}", method = RequestMethod.POST)	
	public String adicionar(@PathVariable("idOs") Long idOs, @ModelAttribute("novoOcamento") @Valid Orcamento novoOrcamento, BindingResult result, Model model){
		
		OrdemDeServico ordemServico = repositorioOrdemDeServico.findOne(idOs);
		Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(ordemServico.getId());
				
		if(result.hasErrors()){
			model.addAttribute("novoOrcamento", new Orcamento());
			model.addAttribute("ordemServico", ordemServico);
			model.addAttribute("tecnicoDaOs", tecnico);
			model.addAttribute("servicos", repositorioServico.findAll());
			model.addAttribute("itens", listaDeItens);	
			model.addAttribute("soma", assistenteOrcamento.somaTotal(itensServico));
			return "orcamento.adicionar.tiles";
		}	
		novoOrcamento.setDataCriacao(new Date());
		novoOrcamento.setStatus(DescricaoStatusOrcamento.NAO_ENVIADO.getString());
		Orcamento novoOrc = repositorioOrcamento.save(novoOrcamento);
		ordemServico.setStatus(DescricaoStatusOs.ORCAMENTO.getString());
		repositorioOrdemDeServico.save(ordemServico);				
		for(ServicoOrcamento itemServ: itensServico) {
			itemServ.setOrcamento(novoOrc);
			repositorioServicoOrcamento.save(itemServ);
		}
		registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.ORCAMENTO_GERADO.getString(), ordemServico, tecnico);
		itensServico = null;
		listaDeItens = null;
		identificadorOS = null;
		return "redirect:/orcamento/lista_orcamento";
	}
	
	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET)
	public String alterar (Model model, @PathVariable("id") Long id, @RequestParam(name = "servicoItem", required=false) Long servicoItem,
			  				  @RequestParam(name = "removeItem", required=false) Long removeItem) {
		
		Orcamento orcamento = repositorioOrcamento.findOne(id);	
		
		if(listaDeItensDoOrc == null || identificadorOrc != id) {
			itensDoOrcamento =  orcamento.getServicoOrcamento();
			listaDeItensDoOrc = null;
			identificadorOrc = null;
			identificadorOrc = id;
		}		
		if(servicoItem != null) {			
			listaDeItensDoOrc = assistenteOrcamento.addServico(servicoItem, itensDoOrcamento);
		}
		if(removeItem != null) {
			listaDeItensDoOrc = assistenteOrcamento.removeServico(removeItem, itensDoOrcamento);
		}
		
		model.addAttribute("orcamento", orcamento);
		model.addAttribute("soma", orcamento.getValorTotal());
		model.addAttribute("servicos", repositorioServico.findAll());		
		if(listaDeItensDoOrc == null){
			model.addAttribute("itens", orcamento.getServicoOrcamento());
		}else {
				model.addAttribute("itens", listaDeItensDoOrc);
				model.addAttribute("soma", assistenteOrcamento.somaTotalSet(itensDoOrcamento));
		}							
		
		return "orcamento.alterar.tiles";		
	}
	
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public String alterar (Model model, @ModelAttribute("orcamento") @Valid Orcamento orcamento, BindingResult result) {
		
		Orcamento orcAtual = repositorioOrcamento.findOne(orcamento.getId());
		
		if(result.hasErrors()){			
			model.addAttribute("servicos", repositorioServico.findAll());					
			return "redirect:/orcamento/alterar/"+ orcamento.getId();
		}
				
		//orcamento.setServicoOrcamento(itensDoOrcamento);	
		orcamento.setDataCriacao(orcAtual.getDataCriacao());
		orcamento.setStatus(orcAtual.getStatus());
		orcamento.setValorTotal(assistenteOrcamento.somaTotalSet(itensDoOrcamento));
		Orcamento orcRef = repositorioOrcamento.save(orcamento);	
		if(itensDoOrcamento != orcAtual.getServicoOrcamento()) {
			repositorioServicoOrcamento.deleteItensOrc(orcamento.getId());
		}
		for(ServicoOrcamento itemServ: itensDoOrcamento) {
			itemServ.setOrcamento(orcRef);
			repositorioServicoOrcamento.save(itemServ);
		}
		registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.ORCAMENTO_EDITADO.getString(), orcamento.getOrdemDeServico(), orcamento.getTecnico());
		itensDoOrcamento = null;
		listaDeItensDoOrc = null;
		identificadorOrc = null;
		return "redirect:/orcamento/lista_orcamento";
	}
	
	@RequestMapping(value = "/aprovar/{id}", method = RequestMethod.GET)
	public String aprovarOrcamento (@PathVariable("id") Long id,
									@RequestParam(name = "aprovar", required=false) Long aprovar) {
		
		Orcamento orcamento = repositorioOrcamento.findOne(id);
		OrdemDeServico os = repositorioOrdemDeServico.findOne(orcamento.getOrdemDeServico().getId());
		Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(orcamento.getOrdemDeServico().getId());
		orcamento.setStatus(DescricaoStatusOrcamento.APROVADO.getString());
		repositorioOrcamento.save(orcamento);
		os.setStatus(DescricaoStatusOs.SERVICO_EM_EXECUCAO.getString());
		repositorioOrdemDeServico.save(os);
		registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.ORCAMENTO_APROVADO.getString(), orcamento.getOrdemDeServico(), tecnico);
		return "forward:/orcamento/lista_orcamento";
	}
	//Operações novo orcamento
	
	private List<ServicoOrcamento> addServico (Long id){
		
		Servico servico = repositorioServico.findOne(id);
		ServicoOrcamento servOrc = new ServicoOrcamento();				
		servOrc.setServico(servico);
		servOrc.setQuantidade(1);
		servOrc.setSubTotal(servico.getValor() * servOrc.getQuantidade());		
		itensServico.add(servOrc);
		return 	itensServico;
	}
	
	private List<ServicoOrcamento> removeServico (Long id){
		
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
	
	//Relatório orcamento
	
	 @RequestMapping(value = "/relatorio_orcamento", method = RequestMethod.GET)
	 public String verReporte(Model model, @RequestParam(name = "format", defaultValue = "pdf", required = false)  String format,
	 						  @RequestParam(name = "num_orc") Long id) {
		 
		List<Orcamento> orcamento = repositorioOrcamento.findAll();	
		if(orcamento != null) {
			 model.addAttribute("format", format);
			 model.addAttribute("datasource", orcamento);				 							
			 
			 return "orcamento_report";
		}
		else {
			return null; // Criar redirect quando report null.
		}		
	 }
	 
	 //Envio de email para o cliente
	 @RequestMapping(value = "/envia_orcamento/{id}", method = RequestMethod.GET)
	 public @ResponseBody String enviaOrcamentoEmail(@PathVariable("id") Long id, Model model){
		 	
		 	Orcamento orcamento = repositorioOrcamento.findOne(id);
		 	Tecnico tecnico = repositorioAcaoTecnica.tecnicoPorIdOs(orcamento.getOrdemDeServico().getId());
		 	String emailCliente = orcamento.getOrdemDeServico().getEquipamento().getCliente().getEnderecoContato().getEmail();
		 	String nomeCliente = orcamento.getOrdemDeServico().getEquipamento().getCliente().getRazaoSocial();
			mailer.enviar(new Mensagem(
					"SIGOS - Orçamento <gilsonalves.developer@gmail.com>", 
					Arrays.asList(emailCliente),
					"Orçamento de manutenção", "Olá! \n\n Segue os dados do orçamento: \n\n Cliente: "
					+ ""+ nomeCliente +"\n\n Valor do orçamento: R$ " + orcamento.getValorTotal()));
			
			orcamento.setStatus(DescricaoStatusOrcamento.ENVIADO.getString());
			repositorioOrcamento.save(orcamento);
			registroDeAcaoTecnica.registrarAcao(DescricaoAcaoTecnica.ORCAMENTO_ENVIADO.getString(), orcamento.getOrdemDeServico(), tecnico);
			return "Email enviado com sucesso!";
	 }	 
}
