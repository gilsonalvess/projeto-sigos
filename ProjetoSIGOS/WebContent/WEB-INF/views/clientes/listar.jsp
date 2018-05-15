<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">

<div class="w3-padding">
	<div class="w3-container w3-painel w3-white">   
		<div class="w3-container"><br>
 		 	<h3>Clientes</h3>
  			<p class="w3-panel w3-border-bottom"></p>
			<div class="form-group form-inline w3-col m5">
    			<div class="form-group">   				    								  	 		
   		 			<input id="inputBuscaCliente" class="w3-input w3-border w3-round"  type="text" placeholder="Localizar cliente">    		 					 				 		 			  		 			
   				</div>
   		 		<button id="pesquisarEquip" class="w3-button w3-theme-d4 w3-round"><i class="fa fa-search"></i> Pesquisar</button><br>  		 		 			  		 			 			
   		 	</div> 
   		 	<a href="/ProjetoSIGOS/cliente/adicionar" ><button class="w3-button w3-theme-d1 w3-right w3-round"><i class="fa fa-user-plus"></i> Novo cliente</button></a>
   		 	<p class="w3-panel"></p>	
			<div class="w3-responsive">
				<table class="w3-table-all" id=tbl-clientes>
					<thead>
						<tr class="w3-theme-l4">
							<th>Cod</th>
							<th>Razão Social</th>
							<th>CNPJ</th>
							<th>Email</th>
							<th>Telefone</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty clientes}">
							<c:forEach items="${clientes}" var="cliente">
								<tr>
									<td>${cliente.id}</td>
									<td>${cliente.razaoSocial}</td>
									<td>${cliente.cnpj}</td>
									<td>${cliente.enderecoContato.email}</td>
									<td>${cliente.enderecoContato.fone}</td>
									<td>
										<a href="/ProjetoSIGOS/cliente/alterar/${cliente.id}" title="Editar" class="w3-rigth w3-border w3-white w3-button w3-round w3-padding-small">
      							 			<i class="fa fa-pencil w3-text-dark-gray"></i>
      							 		</a> 
										<a href="/ProjetoSIGOS/cliente/excluir/${cliente.id}" title="Excluir" class="w3-rigth w3-border w3-white w3-button w3-round w3-padding-small">
      							 			<i class="fa fa-trash w3-text-dark-gray"></i>
      							 		</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<br> 
		</div>
	</div>
	</div>
</sec:authorize>
