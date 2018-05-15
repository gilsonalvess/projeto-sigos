<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="w3-padding">	
	<div class="w3-container w3-painel w3-white"><br> 
		<div class="w3-container">
			<h3>Usuários</h3>    			
  			<p class="w3-panel w3-border-bottom"></p>
  			<div class="form-group">
  				<a href="/ProjetoSIGOS/usuarios/adicionar" class="w3-button w3-round w3-green">+ Novo usuário</a><br>	
  			</div> 
			<p class="w3-panel"></p> 				
  			<div class="w3-responsive">			  			     						  	
				<table id="table-usuarios" class="w3-table-all">
					<thead>
						<tr class="w3-theme-l4">
							<th>Cod</th>
							<th>Nome de usuário</th>
							<th>Role</th>
						</tr>	
					</thead>
					<tbody>
						<c:if test="${!empty usuarios }">
							<c:forEach items="${usuarios}" var="usuario">
								<tr>
									<td>${usuario.id}</td>
									<td>${usuario.username}</td>
									<td>${usuario.role}</td>	
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