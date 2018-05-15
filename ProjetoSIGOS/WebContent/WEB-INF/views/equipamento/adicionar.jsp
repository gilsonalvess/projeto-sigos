<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="actionAdicionar" value="/equipamento/adicionar"></c:url>
<div class="w3-padding">	
	<div class="w3-container w3-painel w3-white"><br>   	
		<form:form action="${actionAdicionar}" method="post"
			modelAttribute="equipamento">
			<div class="w3-container">
				<h3>Novo equipamento</h3>    			
  				<p class="w3-panel w3-border-bottom"></p> 	
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Numero de Série:</label>
							<form:input path="numeroSerie" cssClass="w3-input w3-border w3-round" />
							<form:errors path="numeroSerie" cssStyle="color: red"></form:errors>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Tipo</label>
							 <form:select path="tipoEquipamento.id" style="padding:10px" class="w3-select w3-border w3-round" name="option">
								    <option value="" disabled selected>Selecione um tipo</option>
									<form:options id="itens" items="${tpEquipamento}"  itemLabel="descricao" itemValue="id"/>							  				
							  </form:select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Modelo</label>
							 <form:select path="modeloEquipamento.id" style="padding:10px" class="w3-select w3-border w3-round" name="option">
								    <option value="" disabled selected>Selecione um modelo</option>
									<form:options id="itens" items="${mdEquipamento}"  itemLabel="descricao" itemValue="id"/>							  				
							  </form:select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Cliente</label>
							 <form:select path="cliente.id" style="padding:10px" class="w3-select w3-border w3-round" name="option">
								    <option value="" disabled selected>Selecione o cliente</option>
									<form:options id="itens" items="${cliente}"  itemLabel="razaoSocial" itemValue="id"/>							  				
							  </form:select>
						</div>
					</div>
				</div>
				<input type="submit" value="Salvar" class="w3-button w3-round w3-green"/>
				<a href="/ProjetoSIGOS/equipamento/listar" class="w3-button w3-round w3-theme-l4">Cancelar</a>	
			</div>			
		</form:form>
		<p class="w3-panel"></p> 
	</div>
</div>