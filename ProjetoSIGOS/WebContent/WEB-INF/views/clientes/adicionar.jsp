<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="actionAdicionar" value="/cliente/adicionar"></c:url>

<div class="w3-padding">
	<div class="w3-container w3-painel w3-white">   
		<div class="w3-container"><br>
 		<h3>Clientes</h3>
  		<p class="w3-panel w3-border-bottom"></p> 	
		<form:form class="form-horizontal" action="${actionAdicionar}" method="post" modelAttribute="cliente">		
			<div class="row"></div>	
				<div class="panel-body">
					<div>
						<ol class="breadcrumb">
							<li class="active"> Dados gerais</li>
						</ol>
						<br>
						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-2 control-label" for="cnpj">CNPJ <h11>*</h11>
							</label>
							<div class="col-md-2">
								<form:input id="cnpj" path="cnpj" placeholder="Apenas números"
									class="form-control input-md" type="text" />
								<form:errors path="cnpj" cssStyle="color: red"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="Nome">Razão
								Social <h11>*</h11>
							</label>
							<div class="col-md-8">
								<form:input path="razaoSocial" id="razaoSocial" name="razaoSocial" class="form-control input-md" type="text" />
								<form:errors path="razaoSocial" cssStyle="color: red"></form:errors>
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-2 control-label" for="Nome">Nome
								Fantasia <h11>*</h11>
							</label>
							<div class="col-md-8">
								<form:input path="nomeFantasia" id="nomeFantasia"
									name="nomeFantasia" placeholder=""
									class="form-control input-md" type="text" />
								<form:errors path="nomeFantasia" cssStyle="color: red"></form:errors>
							</div>
						</div>

						<!-- Text input-->

						<div class="form-group">
							<label class="col-md-2 control-label" for="inscrEstadual">Insc.
								Estadual</label>
							<div class="col-md-2">
								<form:input path="inscrEstadual" id="inscrEstadual"
									name="inscrEstadual" placeholder="Apenas números"
									class="form-control input-md" type="text" />
							</div>

							<label class="col-md-2 control-label" for="inscrMunicipal">Insc.
								Municipal</label>
							<div class="col-md-2">
								<form:input path="inscrMunicipal" id="inscrMunicipal"
									name="inscrMunicipal" placeholder="Apenas números"
									class="form-control input-md" type="text" />
							</div>

						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="tipoContrato">Tipo
								de contrato</label>
							<div class="col-md-3">
								<form:select path="tipoContrato.id" class="form-control"
									id="tipoContrato" name="tipoContrato">
									<form:options items="${tpcontratos}" itemLabel="descricao"
										itemValue="id" />
								</form:select>
								<form:errors path="tipoContrato" cssStyle="color: red"></form:errors>
							</div>

						</div>

					</div>					
						<ol class="breadcrumb">
							<li class="active">Contato / Endereço</li>
						</ol>
						<br>
						<!-- Search input-->
						<div class="form-group">
							<label class="col-md-2 control-label" for="CEP">CEP<h11>*</h11></label>
							<div class="col-md-2">
								<form:input path="enderecoContato.cep" id="cep" name="cep"
									placeholder="Apenas números" cssClass="form-control input-md"
									value="" type="search" />
							</div>
							<div class="col-md-2">
								<button id=btnPesquisaCEP type="button" class="btn btn-primary"
									onclick="pesquisacep(cep.value)">Pesquisar</button>
							</div>
						</div>

						<!-- Prepended text-->
						<div class="form-group">
							<label class="col-md-2 control-label" for="prependedtext"></label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon">Logradouro</span>
									<form:input path="enderecoContato.logradouro" id="rua"
										name="rua" cssClass="form-control" readonly="readonly"
										type="text" />
								</div>

							</div>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon">Nº <h11>*</h11></span>
									<form:input path="enderecoContato.numero" id="numero"
										name="numero" cssClass="form-control" type="text" />
								</div>
							</div>

							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">Bairro</span>
									<form:input path="enderecoContato.bairro" id="bairro"
										name="bairro" cssClass="form-control" readonly="readonly"
										type="text" />
								</div>

							</div>

						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="prependedtext"></label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon">Complemento</span>
									<form:input path="enderecoContato.complemento"
										id="complemento" name="complemento" cssClass="form-control"
										type="text" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="prependedtext"></label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon">Cidade</span>
									<form:input path="enderecoContato.cidade" id="cidade"
										name="cidade" cssClass="form-control" readonly="readonly"
										type="text" />
								</div>

							</div>

							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon">Estado</span>
									<form:input path="enderecoContato.uf" id="estado"
										name="estado" cssClass="form-control" readonly="readonly"
										type="text" />
								</div>

							</div>
						</div>
						<!-- Prepended text-->
						<div class="form-group">
							<label class="col-md-2 control-label" for="prependedtext">Telefone
								<h11>*</h11>
							</label>
							<div class="col-md-2">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-earphone"></i></span>
									<form:input path="enderecoContato.fone" id="fone" name="fone"
										cssClass="form-control" placeholder="XX XXXXX-XXXX"
										type="text" maxlength="13"
										pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
										OnKeyPress="formatar('## #####-####', this)" />
								</div>
							</div>
						</div>

						<!-- Prepended text-->
						<div class="form-group">
							<label class="col-md-2 control-label" for="prependedtext">Email
								<h11>*</h11>
							</label>
							<div class="col-md-5">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-envelope"></i></span>
									<form:input path="enderecoContato.email" id="email"
										name="email" cssClass="form-control"
										placeholder="email@email.com" type="text" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="Nome">Contato<h11>*</h11>
							</label>
							<div class="col-md-5">
								<form:input path="enderecoContato.nomeContato"
									id="nomeFantasia" name="nomeFantasia"
									cssClass="form-control input-md" type="text" />
							</div>
						</div>
					</div>
					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Cadastrar"></label>
						<div class="col-md-8">
							<button id="Cadastrar" name="Cadastrar" class="btn btn-success"
								type="Submit">Cadastrar</button>
							<button id="Cancelar" name="Cancelar" class="btn btn-danger"
								type="Reset">Cancelar</button>
						</div>
					</div>
				</div>
		</form:form>
	</div>
</div>