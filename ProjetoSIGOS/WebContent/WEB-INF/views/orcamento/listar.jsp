<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">

<div class="w3-padding">
	<div class="w3-container w3-painel w3-white">   
		<div class="w3-container"><br>
	 		 	<h3>Orçamentos</h3>
	  			<p class="w3-panel w3-border-bottom"></p> 
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
        					<th>Cod</th>
        					<th>Cliente</th>
        					<th>Nº O.S.</th>
        					<th>Status</th>	
        					<th>Ações</th>
      					</tr>
    				</thead>
    				<c:if test="${!empty orcamentos}">
 						<c:forEach items="${orcamentos}" var="orc">
    				 		<tr>    				 			
      							<td class="numero-orc">${orc.id}</td>
      							<td>${orc.ordemDeServico.equipamento.cliente.razaoSocial}</td>  
      							<td><a title="Visualizar O.S." href="/ProjetoSIGOS/ordem_de_servico/visualizar_os/${orc.ordemDeServico.id}">${orc.ordemDeServico.id}</a></td>
      							<td><span id="status-os" class="w3-tag w3-round w3-small w3-khaki">${orc.status}</span></td>      						
      							<td>
      							 	<a href="/ProjetoSIGOS/orcamento/relatorio_orcamento?num_orc=${orc.id}" target="blank" class="w3-border w3-white w3-button w3-round w3-padding-small" 
      							 		title="Imprimir"><i class="fa fa-print"></i>
      							 	</a>
      							 	<a title="Editar" href="/ProjetoSIGOS/orcamento/alterar/${orc.id}"
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
 			 	<c:if test="${empty orcamentos}">			 					
			 		<div class="w3-opacity w3-panel w3-theme-l4">
    					<p class="w3-margin w3-center"><i class="fa fa-exclamation-triangle"></i> Lista vazia!</p>
  					</div>		 									    				 					    					
		    	</c:if>
 			 	<div class="w3-center">
    				<nav>
     					<div class="pagination-os w3-bar w3-border w3-round"></div>
   				 	</nav>
  				</div><br> 				
 			 </div> 
	  		</div>
		</div>  			
	</div>
</sec:authorize>
