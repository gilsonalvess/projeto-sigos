<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table id="table-os" class="w3-table-all">
   					<thead>
      					<tr class="w3-blue-gray">       
        					<th>Número</th>
        					<th>Descricao</th>
        					<th>Qtd</th>
        					<th>Valor</th>
        					
      					</tr>
    				</thead>
    				 <c:if test="${!empty itens}">
 						<c:forEach items="${itens}" var="item">
    				 		<tr>    				 			
      							<td>${item.servico.id}</td>
      							<td>${item.servico.descricao}</td>
      							<td>${item.quantidade}</td>
      							<td>${item.servico.valor}</td>
    						</tr>    						
    					</c:forEach>
			 		</c:if>	
 	</table><br>
	
