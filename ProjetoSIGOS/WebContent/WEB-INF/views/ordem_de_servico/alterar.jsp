<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">

<c:url var="actionIniciarOS" value="/ordem_de_servico/associar_tecnico"></c:url>
<c:url var="actionAlterar" value="/ordem_de_servico/alterar_os/${ordemServico.id}/"></c:url>

<div class="w3-padding"> 	     
	<div class="w3-container w3-painel w3-white">		
           <div class="w3-container"><br>	 	
 		 	<div class="w3-row">	
 		 		<h3>Visualizando O.S.</h3>	      			
 			</div><br> 
  			<p class="w3-panel w3-border-bottom"></p> 	
  			<div class="w3-row">
  				<div class="w3-right form-group">
  					<button id="btn-iniciar" class="w3-button w3-green w3-round" style="display: none;"><i class="fa fa-play"></i> Iniciar</button>		
  					<a id="btn-imprimir" href="/ProjetoSIGOS/ordem_de_servico/relatorio_analitico?num_os=${ordemServico.id}" target="_blank"
  						class="w3-button w3-round w3-theme-l1"><i class="fa fa-print" aria-hidden="true" title="Imprimir relatório analítico"></i> Imprimir</a>
  					<a href="/ProjetoSIGOS/orcamento/novo_orcamento/${ordemServico.id}" id="btn-orcamento" class="w3-round w3-button tablink w3-green">Criar orçamento</a>
  					<button id="btn-finalizar-os" class="w3-button w3-green w3-round" style="display: none;"> Finalizar</button>
  					  					
  				</div>
  				<a href="/ProjetoSIGOS/ordem_de_servico/lista_os" title="Voltar para lista de O.S" class="w3-button  w3-theme-d1 w3-round"><i class="fa fa-arrow-left"></i>  Voltar</a>
  			</div>  				
  			<form:form id="actionForm" action="${actionIniciarOS}" method="post" modelAttribute="iniciarOs">	 
  				
  				<!-- Id OS para localizar o tecnico -->	
  				
  				<form:hidden id ="id_os" path="ordemDeServico.id" value="${ordemServico.id}"/>  							 					
  				  				 		 			 		 		
   		 	<!-- Dados da O.S -->  		 		
   		 	<div class="w3-panel w3-round w3-card "><br><br>
   		 		 <div class="w3-row">  		 		    		 		 	 		 		 	
   		 		 	<div class="w3-right">
   		 		 		<p>Criada em: <fmt:formatDate value="${ordemServico.dataAbertura}" pattern="dd/MM/yyyy HH:mm" timeZone="America/Sao_Paulo"/></p>
   		 		 	</div>
   		 		 	<h4>O.S Nº: <strong class="w3-text-red">${ordemServico.id}</strong></h4>
   		 		 	<h5 id="status-os" class="w3-text-gray"><i>${ordemServico.status}</i></h5>  
   		 		 </div>
   		 		
   		 		 <p class="w3-panel w3-border-bottom"></p>
   		 		 	<div class="w3-panel w3-round w3-light-gray">   		 			
   		 				<h5>Cliente</h5>
   		 			</div>		  		 			 			  	
   		 			<div class="w3-container" >						
   		 				<div class="form-group w3-third">
   		 					<label>Nome</label>
   		 					<p>${ordemServico.equipamento.cliente.nomeFantasia}</p>  
   		 				</div>  		 				
   		 				<div class="form-group w3-third">
   		 					<label>Email</label>															
							<p>${ordemServico.equipamento.cliente.enderecoContato.email}<p/>
						</div>
						<div class="form-group w3-third">
							<label>Telefone</label>	  							
							<p>${ordemServico.equipamento.cliente.enderecoContato.fone }</p> 
						</div>
   		 			</div>    		 				 		
					<!-- <p class="w3-panel w3-border-bottom"></p> -->
					
					<div class="w3-panel w3-round w3-light-gray">
						<h5>Equipamento</h5>
					</div>												 		 		 		
   		 			<div class="w3-container" >
   		 				<div class="form-group w3-third"> 
   		 					<label>Nº de Série</label> 		 			  		 				  	  		 				   		 					
							<p>${ordemServico.equipamento.numeroSerie}</p>
						</div>
						<div class="form-group w3-third">
   		 					<label>Tipo / Modelo</label> 
   		 					<p>${ordemServico.equipamento.tipoEquipamento.descricao} - ${ordemServico.equipamento.modeloEquipamento.descricao}</p>			  															 
						</div>
						<div class="form-group w3-third">
							<a id="btn-alterar-equip" title="Alterar equipamento" class="w3-button w3-theme-d1 w3-round w3-padding-small w3-round w3-right"><i class="fa fa-pencil"></i> Alterar</a>
						</div>																				 		 		 		 			   		 			
					</div>
					<div id="divTecnico" style="display: none;">
						<div class="w3-panel w3-round w3-light-gray"> 
							<h5>Tecnico do serviço</h5>
						</div>												 		 		 		
	   		 			<div class="w3-container" >	   		 				
	   		 				<div class="form-group w3-third"> 
	   		 					<label>Matrícula</label> 		 			  		 				  	  		 				   		 					
								<p id=matTec>${tecnicoDaOs.matricula}</p>
							</div>
							<div class="form-group w3-third">
	   		 					<label>Nome</label> 
	   		 					<p>${tecnicoDaOs.nome}</p>			  															 
							</div>		
							<div class="form-group w3-third">
								<a id="btn-alterar-tec" title="Alterar técnico" class="w3-button w3-theme-d1 w3-round w3-padding-small w3-round w3-right"><i class="fa fa-pencil"></i> Alterar</a>
	   		 					<label>Telefone</label> 
	   		 					<p>${tecnicoDaOs.telefone}</p>		   		 								  															 
							</div>																						 		 		 		 			   		 			
						</div>						
					</div>
					<hr>
					<!-- Informações adicionais -->
					<div class="w3-container">
						<h5>Informações Adicionais:</h5>
					</div>					
					<div class="w3-border w3-round"><br>												
						<div class="w3-container">
							<label>Defeito relatado pelo cliente:</label> 		 			  		 				  	  		 				   		 					
							<p>${ordemServico.infoDefeito}</p>								
							<label>Observações:</label> 		 			  		 				  	  		 				   		 					
							<p>${ordemServico.observacoes}</p>
						</div>		 											
					</div>
					<br>				   
				<!-- Fim dados da O.S -->		 	   	 	 		 	 	  		 
   			</div>

				<!-- Modal selecionar tecnico -->
				<div>
					<div class="w3-container">
					  	<div id="id-modal-tecnico" class="w3-modal">
					   	 <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="padding:32px;max-width:600px">
					    <header class="w3-container"> 
					     	<span onclick="document.getElementById('id-modal-tecnico').style.display='none'" 
					        	class="w3-button w3-display-topright">&times;</span>
					  	<h3>Técnicos</h3>
					  	<p class="w3-panel w3-border-bottom"></p>
					 	</header><br>
				      	<div class="w3-container">				        	
				         	<form:select path="tecnico.id" class="w3-select w3-border">
				            	<option  value="" disabled selected>Selecione um técnico</option>
				           		<form:options id="selectTec" items="${tecnicos}" itemLabel="nome" itemValue="id"/>
				         	</form:select>
				          	<p class="w3-panel"></p>        
				          	<button id="btn" type="submit" class="w3-button w3-green w3-round">Confirmar</button>
				         	 <a
				            	onclick="document.getElementById('id-modal-tecnico').style.display='none'" 
				            	class="w3-button w3-round w3-theme-l4" data-dismiss="modal">Cancelar
				         	 </a>       
				      	</div>
				      	<br><br>
				    	</div>
				 	 </div>
					</div>
				<!-- Fim Modal selecionar tecnico -->
				</div> 	
				
				<!-- Modal selecionar equipamento -->
				<div>
					<div class="w3-container">
					  	<div id="id-modal-equip" class="w3-modal">
					   	 <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="padding:32px;max-width:600px">
					    <header class="w3-container"> 
					     	<span onclick="document.getElementById('id-modal-equip').style.display='none'" 
					        	  class="w3-button w3-display-topright">&times;</span>
					  	<h3>Alterar Equipamento</h3>
					  	<p class="w3-panel w3-border-bottom"></p>
					 	</header><br>
				      	<div class="w3-responsive">	
				      	
				      		<table id="table-equip" class="w3-table-all">
   								<thead>
      								<tr class="w3-light-gray">   
      									<th></th>   								
        								<th>ID</th>
        								<th>Tipo/Modelo</th>
        								<th>Num. Série</th>       					
      								</tr>
    							</thead>
    						<c:if test="${!empty equipamentos}">
 								<c:forEach items="${equipamentos}" var="equip">
    				 				<tr>
    				 					<td id="id-novo-equip"><input type="radio" name="gender" value="${equip.id}"/></td>   				 			
      									<td id="id-equip">${equip.id}</td>
      									<td>${equip.tipoEquipamento.descricao} - ${equip.modeloEquipamento.descricao}</td>  
      									<td>${equip.numeroSerie}</td>      						
    								</tr>    						
    							</c:forEach>
			 				</c:if>	 		
 			 				</table><br>				     								      			        
				          		<p class="w3-panel"></p>        
				          		<button id="alterarEquip" type="submit" class="w3-button w3-green w3-round">Confirmar</button>
				         	 	<a	onclick="document.getElementById('id-modal-equip').style.display='none'" 
				            		class="w3-button w3-round w3-theme-l4" data-dismiss="modal">Cancelar</a>       
				      		</div>
				      		<br><br>
				    		</div>
				 	 	</div>
					</div>
				<!-- Fim Modal selecionar equipamento -->
				</div><br>   			
			</form:form>			
  		</div>
        </div>  		
	</div>
<script>
	$(document).on("click", "#alterarEquip", function() {
		var radioValue = $("input[name='gender']:checked").val();	
		if(radioValue == null){          
	      	alert("Nenhum equipamento selecionado!");               
	    }
		else{			
	        $("#actionForm").attr("action", "${actionAlterar}" + radioValue);	  
	    }	    		
	});

</script>
</sec:authorize>