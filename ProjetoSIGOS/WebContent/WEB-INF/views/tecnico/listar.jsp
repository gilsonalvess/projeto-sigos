<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="w3-padding">	
	<div class="w3-container w3-painel w3-white"><br> 
		<div class="w3-container">
			<h3>Tecnicos</h3>    			
  			<p class="w3-panel w3-border-bottom"></p>
  			<div class="form-group">
  				<a href="#" class="w3-button w3-round w3-green">+ Novo técnico</a><br>	
  			</div> 
			<p class="w3-panel"></p> 				
  			<div class="w3-responsive">			  			     						  	
				<table id="table-equipamentos" class="w3-table-all">
					<thead>
						<tr class="w3-theme-l4">
							<th>Cod</th>
							<th>Nome</th>
							<th>Matricula</th>	
							<th>Fone</th>								
						</tr>	
					</thead>
					<tbody>
						<c:if test="${!empty tecnicos }">
							<c:forEach items="${tecnicos}" var="tecnico">
								<tr>
									<td>${tecnico.id}</td>
									<td>${tecnico.nome}</td>
									<td>${tecnico.matricula}</td>
									<td>${tecnico.telefone}</td>																											
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
                 