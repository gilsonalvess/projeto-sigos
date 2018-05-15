<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>

<!-- Top container -->

<div class="w3-top">
  <div class="w3-bar w3-theme-d3 w3-left-align" style="z-index:3">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i></button>
  	<a href="/ProjetoSIGOS/home/painel" class="w3-bar-item w3-padding-large w3-theme-d4"><i class="fa fa-home"></i> SIGOS</a>
  	<div class="w3-right">  		  
 		<a class="w3-button w3-padding-large" title="Notifications"><i class="fa fa-envelope"></i></a>		
 		<div class="w3-dropdown-hover w3-hide-small">
    		<button class="w3-button w3-padding-large" title="Usuário da sessão"><i class="fa fa-user"></i> ${principal.username}</button>     
    		<div class="w3-dropdown-content w3-card-4 w3-bar-block" style="width:100px">     
      			<a href="/ProjetoSIGOS/logout" class="w3-bar-item w3-button" title="Sair"><i class="fa fa-sign-out fa-fw" aria-hidden="true"></i> Sair</a>
    		</div>
 		</div>
 		<sec:authorize access="hasRole('ROLE_ADMIN')">
	 		<div class="w3-dropdown-hover w3-hide-small">
	    		<a class="w3-button w3-padding-large"><i class="fa fa-cog fa-fw"></i>Definições</a>     
	    		<div class="w3-dropdown-content w3-card-4 w3-bar-block" style="width:200px">     	      			
	    			<a href="#" class="w3-bar-item w3-button" title="Cadastro modelo de equipamento">Modelo Equipamento</a>
	      			<a href="#" class="w3-bar-item w3-button" title="Cadastro tipo de equipamento">Tipo Equipamento</a>
	      			<a href="/ProjetoSIGOS/servico/listar" class="w3-bar-item w3-button" title="Cadastro de serviços"><i class="fa fa-wrench fa-fw" aria-hidden="true"></i> Serviços</a>
	      			<a href="/ProjetoSIGOS/usuarios/listar" class="w3-bar-item w3-button" title="Usuários do sistema"><i class="fa fa-user fa-fw" aria-hidden="true"></i> Usuários</a>	      			
	    		</div>
	 		</div>
 		</sec:authorize>
 	</div>	 
 </div>
</div>
</sec:authorize>
