<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">

<div class="w3-padding">
	<div class="w3-container w3-painel w3-white">   
		<div class="w3-container"><br>
 		 	<h3>Ordens de serviço</h3>
  			<p class="w3-panel w3-border-bottom"></p> 
  			<sec:authorize access="hasRole('ROLE_ADMIN')">			
				<a href="/ProjetoSIGOS/ordem_de_servico/nova_os"><button class="w3-button w3-green w3-round"> + Abrir nova O.S</button></a>  				  	
  			</sec:authorize>		
  			<div class="w3-right form-group">
  				<button class="w3-button w3-theme-d1 w3-round">Hoje</button>
  				<button class="w3-button w3-theme-l1 w3-round">7 dias</button>
  				<button class="w3-button w3-theme-l1 w3-round">15 dias</button>	 				
  			</div> 		
  			<p class="w3-panel"></p> 			
  			<div class="form-group">
			    <select name="state" id="maxRows" class="form-control" style="width: 150px">
			      <option value="100">Todas</option>
			      <option value="3">3</option>
			      <option value="5">5</option>
			      <option value="10">10</option>      
			    </select>
  			</div>   				
 			<div class="w3-responsive">			
  				<table id="table-os" class="w3-table-all">
   					<thead>
      					<tr class="w3-theme-l4">       
        					<th>Nº O.S</th>
        					<th>Cliente</th>
        					<th>Equipamento</th>
        					<th>Status</th>	
        					<th>Ações</th>
      					</tr>
    				</thead>
    				<c:if test="${!empty ordensServico}">
 						<c:forEach items="${ordensServico}" var="os">
    				 		<tr>    				 			
      							<td class="numero-os">${os.id}</td>
      							<td>${os.equipamento.cliente.razaoSocial}</td>  
      							<td>${os.equipamento.tipoEquipamento.descricao} - ${os.equipamento.modeloEquipamento.descricao}</td>
      							<td><span id="status-os" class="w3-tag w3-round w3-small w3-khaki">${os.status}</span></td>      						
      							<td>
      							 	<button class="w3-border w3-white w3-button w3-round w3-padding-small" 
      							 		onclick="document.getElementById('id-modal-info-os').style.display='block'" id="btn-info-os" title="Detalhes" >
      							 		<i class="fa fa-info-circle"></i>
      							 	</button>
      							 	<a title="Editar" href="/ProjetoSIGOS/ordem_de_servico/visualizar_os/${os.id}"
      							 	 	class="w3-border w3-button w3-white w3-button w3-round w3-padding-small">    							 			
      							 		<i class="fa fa-pencil"></i>	 		
      							 	</a>
      							 	<button title="Excluir" class="w3-rigth w3-border w3-white w3-button w3-round w3-padding-small">
      							 		<i class="fa fa-trash w3-text-dark-gray"></i>
      							 	</button>     							 									  		 						    	   							    							   														
  	  							</td>
    						</tr>    						
    					</c:forEach>
			 		</c:if>	 		
 			 	</table>
 			 	<c:if test="${empty ordensServico}">			 					
			 		<div class="w3-opacity w3-panel w3-theme-l4">
    					<p class="w3-margin w3-center"><i class="fa fa-exclamation-triangle"></i> Lista vazia!</p>
  					</div> 			 									    				 					    					
    			</c:if>
 			 	<div class="w3-center w3-margin">
    				<nav>
     					<div class="pagination-os w3-bar w3-border w3-round"></div>
   				 	</nav>
  				</div><br> 				
 			 </div> 
 			    
 			 <!-- Modal Informações OS -->	
 			 <div>
				<div class="w3-container">
				  	<div id="id-modal-info-os" class="w3-modal">
				   	 <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="padding:32px;max-width:800px">
				    <header class="w3-container"> 
				     	<span onclick="document.getElementById('id-modal-info-os').style.display='none'" 
				     		  class="w3-button w3-display-topright">&times;</span>
					  	<h3 id="title">Andamento da O.S.</h3>					  	
					  	<p class="w3-panel w3-border-bottom"></p>
					  	<!-- dados O.S -->
					  	<p class="w3-right" id="data-abertura"></p>
					  	<div>					  		
					  		<p id="id-nome-cliente"></p>
					  		<p id="id-nome-tecnico"></p>
					  		<p id="id-ns-equip"></p>					  		
					  	</div>
					 	</header><br>					 	
				      	<div class="w3-responsive">					      
				      		<table id="tbl-info-os" class="w3-table-all">				      		
   								<thead>
      								<tr class="w3-light-gray">
										<th>Acão técnica</th>     								
        								<th>Data</th>        								     					
      								</tr>
    							</thead>									
    				 				<tr>	
    				 					<td></td>		
      									<td></td>        									      						
    								</tr>    							
 			 				</table><br>				     								      			        
				          	<p class="w3-panel"></p>        		          		
				         	<a	onclick="document.getElementById('id-modal-info-os').style.display='none'" 
				            		class="w3-button w3-right w3-round w3-theme-l4" data-dismiss="modal">Sair</a>       				      		
				         </div><br>			      		
		    			</div>
		 	 		</div>
				</div>			
			</div>
			<!-- Fim modal informações OS -->
		</div>
	</div>	
</div>

</sec:authorize>
