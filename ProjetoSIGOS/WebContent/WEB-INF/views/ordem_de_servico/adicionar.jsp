<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('ROLE_ADMIN')">

<c:url var="actionAdicionarOS" value="/ordem_de_servico/nova_os"></c:url>
	
<div class="w3-padding">	
	<div class="w3-container w3-painel w3-white"><br>
		<div class="w3-container">	
	    	<h3>Nova ordem de serviço</h3>    			
	  		<p class="w3-panel w3-border-bottom"></p>  		 	
	  		<div class="w3-row">
	  			<div class="w3-right form-group">
	  				<button class="w3-button w3-theme-d1 w3-round"><i class="fa fa-user-plus"></i> Cliente</button>
	  				<button class="w3-button w3-theme-d1 w3-round">+ Equipamento</button>
	  			</div>
	  		</div> 	
	  		<div class="form-group form-inline w3-col m5">
	    		<div class="form-group">   				    								  	 		
	   		 		<input id="inputBuscaEquip" class="w3-input w3-border w3-round" type="text" placeholder="Localizar equipamento"/>					 				 		 			  		 			
	   			</div>
	   		 	<button id="pesquisarEquip" class="w3-button w3-theme-d4 w3-round"><i class="fa fa-search"></i> Pesquisar</button><br>			 			  		 			 			
	   		 </div>  
	   		 			
	  		<form:form action="${actionAdicionarOS}" method="post" modelAttribute="novaOS"><br> 
	  		
	  			<input id="num_os" type="hidden" value="${id_os}"/>
	  			<input id="sucesso" type="hidden" value="${sucesso}"/>
	  			
	  			<!-- Alerta equipamento não localizado -->
	  			<div id="alerta-nao-localizado" class="w3-row" style="display: none;">
					<div class="w3-panel w3-round w3-pale-yellow w3-round">
		    			<h4><i class="fa fa-exclamation-circle"></i> Equipamento não localizado!</h4>
		    			<p>Favor verificar o número de série.</p>
		  			</div>
				</div>
				<!-- Alerta equipamento em O.S. não finalizada -->
				<div id="alerta-equip-os" class="w3-row" style="display: none;">
					<div class="w3-panel w3-round w3-pale-yellow w3-round">
		    			<h4 class="w3-margin"><i class="fa fa-exclamation-circle"></i> O equipamento já está vinculado a uma O.S. não finalizada!</h4>	    		
		  			</div>
				</div>	 		 			 	
	  			<div style="display: none;" id="dadosOS" class="w3-row">
	  					 		 			 		 		
	   		 		<!-- Dados da O.S -->
	   		 		<div class="w3-panel w3-leftbar w3-round w3-card"><br>		  		 			 			  	  		 			 		 			  		 				  	  		 										  									
						 	
						<div class="w3-panel w3-round w3-light-gray"> 
							<h5>Equipamento</h5>
						</div>												 		 		 		
	   		 			<div class="w3-container" >
	   		 			<form:input cssStyle="display:none;" id="id_equipamento" path="equipamento.id" type="text"></form:input>
	   		 				<div class="form-group w3-third"> 
	   		 					<label>Nº de Série</label> 		 			  		 				  	  		 				   		 					
								<p id="numeroSerie"></p>
							</div>
							<div class="form-group w3-third">
	   		 					<label>Tipo / Modelo</label> 
	   		 					<p id="modelo"></p> 			  															 
							</div>														 		 		 		 			   		 			
						</div>
						  
						<div class="w3-panel w3-round w3-light-gray">   		 			
	   		 				<h5>Cliente</h5>
	   		 			</div>		  		 			 			  	
	   		 			<div class="w3-container" >						
	   		 				<div class="form-group w3-third">
	   		 					<label>Nome</label>
	   		 					<p id="nomeCliente"></p>  
	   		 				</div>  		 				
	   		 				<div class="form-group w3-third">
	   		 					<label>Email</label>															
								<p id="emailCliente"></p>  	
							</div>
							<div class="form-group w3-third">
								<label>Telefone</label>	  							
								<p id="foneCliente"></p> 		
							</div>
	   		 			</div>  													 		 		 		 			   		 			
					</div>  																	  		 			
					<!-- Fim dados da O.S -->		 	   		 			 	  		 
	   			</div>
	   				<!-- Modal alerta -->
						<div>
							<div class="w3-panel">
							  	<div id="id-alert-termo" class="w3-modal">
							   	 <div class="w3-modal-content w3-card-4 w3-round w3-animate-zoom" style="padding:42px;max-width:600px">
								 	<h4><i class="fa fa-info-circle" aria-hidden="true"></i> O.S. Criada com sucesso!</h4>	
								 	<h5> Deseja imprimir o termo de recebimento?</h5>
								 	<hr>							 						 	
								 	<a href="http://localhost:8080/ProjetoSIGOS/ordem_de_servico/termo_de_receb_equip?num_os=${id_os}" 
								 		target="blank" id="btn-ok-os" onclick="document.getElementById('id-alert-termo').style.display='none'" 
							            class="w3-button w3-round w3-green">Sim
							        </a>
							        <a href="http://localhost:8080/ProjetoSIGOS/ordem_de_servico/lista_os"
					            		onclick="document.getElementById('id-alert-termo').style.display='none'" 
					            		class="w3-button w3-round w3-theme-l4" data-dismiss="modal">Não
					         	 	</a><br>   					      
						    	</div>
						 	 </div>
							</div>
						</div>
					<!-- Fim Modal alerta -->
				<br>
	     		<div class="row form-group w3-row">	
	 				<div class="form-group col-md-6"> 		
	 					<label class="w3-text-grey" for="obs">Defeito apresentado:</label><br>
	    				<form:textarea id = "obs-defeito" path="infoDefeito" class="w3-input w3-border w3-round" placeholder="Informe o defeito relatado pelo cliente." rows="4" style="resize:none"></form:textarea>
	    			</div>
				</div>
				<div class="row form-group w3-row">	
	 				<div class="form-group col-md-6"> 		
	 					<label class="w3-text-grey" for="obs">Observações:</label><br>
	    				<form:textarea id = "obs" path="observacoes" class="w3-input w3-border w3-round" placeholder="Informe algum detalhe relevante para o técnico." rows="3" style="resize:none"></form:textarea>
	    			</div>
				</div>
				<div class="row"></div>
					<button id="btn-salva-os" disabled class="w3-button w3-green w3-round" type="submit">Salvar</button> 
					<a class="w3-button w3-round w3-theme-l4" href="/ProjetoSIGOS/ordem_de_servico/lista_os">Cancelar</a>			
				</form:form><br>	
			</div>		
		</div>
	</div>
</sec:authorize>