<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="actionAdicionar" value="/usuarios/adicionar"></c:url>
<div class="w3-padding">	
	<div class="w3-container w3-painel w3-white"><br>   	
		<form:form action="${actionAdicionar}" method="post"
			modelAttribute="usuario">
			<div class="w3-container">
				<h3>Novo usuário</h3>    			
  				<p class="w3-panel w3-border-bottom"></p> 	
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Nome de usuário:</label>
							<form:input path="username" cssClass="w3-input w3-border w3-round" />
							<form:errors path="username" cssStyle="color: red"></form:errors>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Senha:</label>
							<form:password path="password" cssClass="w3-input w3-border w3-round" />
							<form:errors path="password" cssStyle="color: red"></form:errors>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Perfil:</label>
							<select name="role" class="w3-select w3-border w3-round">
								<option value="ROLE_ADMIN"> Administrador</option>
								<option value="ROLE_USER"> Usuário comum</option>							
							</select>
						</div>
					</div>
				</div>
				<input type="submit" value="Salvar" class="w3-button w3-round w3-green"/>
				<a href="/ProjetoSIGOS/usuarios/listar" class="w3-button w3-round w3-theme-l4">Cancelar</a>	
			</div>			
		</form:form>
		<p class="w3-panel"></p> 
	</div>
</div>