<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="w3-padding">	
	<div class="w3-container w3-painel w3-white"><br> 
		<div class="w3-container">
			<h3>Serviços</h3>    			
  			<p class="w3-panel w3-border-bottom"></p>
  			<div class="form-group">
  				<a href="#" class="w3-button w3-round w3-green">+ Novo serviço</a><br>	
  			</div> 
			<p class="w3-panel"></p> 				
  			<div class="w3-responsive">			  			     						  	
				<table id="table-equipamentos" class="w3-table-all">
					<thead>
						<tr class="w3-theme-l4">
							<th>Cod</th>
							<th>Descrição</th>
							<th>Valor</th>							
						</tr>	
					</thead>
					<tbody>
						<c:if test="${!empty servicos }">
							<c:forEach items="${servicos}" var="servico">
								<tr>
									<td>${servico.id}</td>
									<td>${servico.descricao}</td>
									<td>${servico.valor}</td>																											
								</tr>
							</c:forEach>		
						</c:if>	
					</tbody>
				</table>
			</div>
			<br/>			
		</div><br>
	</div>
</div>
                 