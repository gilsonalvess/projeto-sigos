<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<sec:authorize access="isAuthenticated()">

<c:url var="actionAlterar" value="/orcamento/alterar"></c:url>

<div class="w3-padding">
	<div class="w3-container w3-painel w3-white"><br>
		<div class="w3-container">
 		 	<h3>Alterar Orçamento</h3>
  			<p class="w3-panel w3-border-bottom"></p> 			 				
  			 <!-- Resumo da OS -->
  			<div class="w3-panel w3-round w3-card"><br>
  				<h5>Resumo da O.S.</h5>  	 
  				<hr>		   		 			 		 				
  				<div class="w3-container" >						
   		 			<div class="form-group w3-third">
   		 			<label>Nº da OS</label>
   		 				<p> ${orcamento.ordemDeServico.id}</p> 
   		 			</div>  		 				
   		 			<div class="form-group w3-third">
   		 				<label>Técnico do serviço</label>															
						<p>${orcamento.tecnico.nome}<p/>
					</div>
					<div class="form-group w3-third">
						<label>Equipamento - Nº Série</label>	  							
						<p>${orcamento.ordemDeServico.equipamento.numeroSerie}</p> 
					</div>
   		 		</div> 
  			</div>
  		<div class="w3-row">
  			<button id="btn-enviar" class="w3-button w3-green w3-round w3-theme-d1"><i class="fa fa-paper-plane" aria-hidden="true"></i> Enviar</button>
  			<div class="w3-right">
  				<button id="btn-aprovar" class="w3-button w3-round w3-green"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Aprovar</button>
  				<button class="w3-button w3-round w3-theme-l1"><i class="fa fa-print" aria-hidden="true"></i> Imprimir</button>			  			
				<button class="w3-button w3-round w3-theme-l1"><i class="fa fa-thumbs-o-up fa-rotate-180" aria-hidden="true"></i> Reprovar</button>
  			</div>							
  		</div>  				 
  		<!-- Dados do orçamento -->
  		<form:form action="${actionAlterar}" method="post" modelAttribute="orcamento">
  			<input style="display: none;" id="id-orc" type="text" value="${orcamento.id}"/>
  			<form:hidden path="ordemDeServico.id" value="${orcamento.ordemDeServico.id}"/>
  			<form:hidden path="tecnico.id" value="${orcamento.tecnico.id}"/>
  			<form:hidden path="id" value="${orcamento.id}"/>
		  	<div class="w3-row">		  		
	  			<div class="w3-panel w3-round w3-card"><br>
	  				<h5>Orçamento: ${orcamento.id}</h5>
	  				<p class="w3-right w3-text-theme">Criado em: <fmt:formatDate value="${orcamento.dataCriacao}" pattern="dd/MM/yyyy - HH:mm" timeZone="America/Sao_Paulo"/></p>
	  				<p><span class="w3-text-theme w3-tag w3-round w3-khaki">${orcamento.status}</span></p>	  				
	  				<hr>
	  				<div class="w3-panel w3-round w3-light-gray">   		  			
   		 				<h5>Cliente</h5>
   		 			</div>		  		 			 			  	
   		 			<div class="w3-container">						
   		 				<div class="form-group w3-third">
   		 					<label>Nome</label>
   		 					<p>${orcamento.ordemDeServico.equipamento.cliente.nomeFantasia}</p>  
   		 				</div>  		 				
   		 				<div class="form-group w3-third">
   		 					<label>Email</label>															
							<p>${orcamento.ordemDeServico.equipamento.cliente.enderecoContato.email}<p/>
						</div>
						<div class="form-group w3-third">
							<label>Telefone</label>	  							
							<p>${orcamento.ordemDeServico.equipamento.cliente.enderecoContato.fone }</p> 
						</div>
   		 			</div> 
   		 			<hr>   		 				 						
	  					<div class="w3-row">
				  			<div class="w3-half" style="margin-right:5px">
		  						 <form:select path="" style="padding:10px" id="select-item-orc" class="w3-select w3-border w3-round" name="option">
								    <option value="" disabled selected>Selecione um serviço</option>
									<form:options id="itens" items="${servicos}"  itemLabel="descricao" itemValue="id"/>							  				
							  	</form:select>
		  					</div>		  					
		 						<a href="#" id="add-row" class="w3-button w3-round w3-theme-d2 w3-right">+ Novo serviço</a>			 									
 						</div><br>
		  				<div id="div-table" class="w3-responsive">			
			  				<table id="table-servico" class="table table-bordered">
			   					<thead>
			      					<tr class="w3-center w3-light-gray">       
			        					<th>Cod</th>
			        					<th>Descrição</th>
			        					<th>Quantidade</th>
			        					<th>Valor</th>	
			        					<th>Subtotal</th>
			      					</tr>
			    				</thead>
			    				<tbody id="row-item">			    					
    								<c:if test="${!empty itens}">
 										<c:forEach items="${itens}" var="item">
    				 						<tr>     				 									   				 			
      											<td class="numero-serv w3-center" style="width:8%">${item.servico.id}</td>
      											<td>${item.servico.descricao}</td>
      											<td class="w3-center" style="width:10%"><input style="padding:3px" class="w3-input w3-border w3-round" type="text" value="${item.quantidade}"/></td>      											
      											<td style="width:15%"><fmt:formatNumber type="currency" value="${item.servico.valor}"/></td>
      											<td style="width:15%"><fmt:formatNumber type="currency" value="${item.subTotal}"/>	
      												<a id="remove-item-orc" class=" w3-right w3-border w3-white w3-button w3-round w3-padding-small"><i class="fa fa-times" aria-hidden="true"></i></a>
      											</td>     											
    										</tr>     										 						
    									</c:forEach>
			 						</c:if>				 							
			    				<tbody>		    				    														
			 			 	</table>	
			 			 	<c:if test="${empty itens}">			 					
			 					<div class="w3-opacity w3-panel w3-theme-l4">
    								<p class="w3-margin w3-center"><i class="fa fa-exclamation-triangle"></i> Nenhum item selecionado!</p>
  								</div> 	  																		 									    				 					    					
		    				</c:if>			    					
		  					</div>
			  					<div class="w3-padding">			  										  				 			  									  							
			  						<p class="money w3-right">Total: <fmt:formatNumber type="currency" value="${soma}"/></p>
			  						<form:hidden path="valorTotal" value="${soma}"/> 									  							 																	  						
			  					</div>
			  					<div class="w3-row">	
					 			<div class="form-group">
					 				<br> 		
					 				<label class="w3-text-grey" for="obs">Informações adicionais</label><br>
					    			<form:textarea path="infoAdicionais" id ="obs" class="w3-input w3-border w3-round" 
					    						   placeholder="Descreva informações que possam ser relevantes para o cliente." rows="4" style="resize:none"></form:textarea>
					   			</div>
								</div><br>	  						
		  					</div>			  											 							 							 																					
		 				</div>	
		 			<!-- Modal alerta -->					
						<div class="w3-panel">
						  	<div id="id-alert-aprovado" class="w3-modal">
						   	 <div class="w3-modal-content w3-card-4 w3-round w3-animate-zoom" style="padding:42px;max-width:600px">
							 	<h4><i class="fa fa-info-circle" aria-hidden="true"></i> Orçamento aprovado com sucesso!</h4>	
							 	<hr>					 	
							 	<a id="btn-ok" onclick="document.getElementById('id-alert-aprovado').style.display='none'" 
						           class="w3-button w3-round w3-theme-l4 w3-right" data-dismiss="modal">Ok</a><br>   					      
					    	</div>
					 	 </div>
						</div>
						
						<div id="id-modal-enviando" class="w3-modal">
						 	<div class="w3-modal-content w3-card-4 w3-round w3-animate-zoom" style="padding:24px;max-width:300px">
								 <h5 class="w3-center"><i class="fa fa-spinner fa-pulse w3-spin" style="font-size:48px"></i><br><br>Enviando...</h5>
							</div>
						</div>		
								
				<!-- Fim Modal alerta -->
		 				<button class="w3-button w3-round w3-green">Salvar</button>		
		 				<a href="/ProjetoSIGOS/orcamento/lista_orcamento" class="w3-button w3-round w3-theme-l4">Cancelar</a>		
		 				<div id="div"></div>	  			
		  			</form:form>																	
	  				<br>	
 				</div><br>
 			</div>
 		</div>
</sec:authorize>
