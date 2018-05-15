<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:4;width:240px;" id="mySidebar"><br>
  		<div class="w3-container">  
    		<div class="w3-col s9 w3-bar">
      			<a href="/ProjetoSIGOS/home/painel"><h5><i class="fa fa-tachometer"></i> Dashboard</h5></a>
    		</div>
  		</div>
  <hr>
  <div class="w3-bar-block">
    <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Fechar Menu</a>
    <a href="/ProjetoSIGOS/ordem_de_servico/lista_os" class="w3-hover-theme w3-bar-item w3-button w3-padding"><i class="fa fa-file-text fa-fw"></i> Ordens de Serviço</a>
    <a href="/ProjetoSIGOS/orcamento/lista_orcamento" class="w3-hover-theme w3-bar-item w3-button w3-padding"><i class="glyphicon glyphicon-usd fa-fw"></i> Orçamentos</a>  
    <a href="/ProjetoSIGOS/cliente/listar" class="w3-hover-theme w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i> Clientes</a> 
    <a href="/ProjetoSIGOS/equipamento/listar" class="w3-hover-theme w3-bar-item w3-button w3-padding"><i class="fa fa-fax fa-fw"></i> Equipamentos</a>    
   	<sec:authorize access="hasRole('ROLE_ADMIN')"> 
  		 <a href="/ProjetoSIGOS/tecnico/listar" class="w3-hover-theme w3-bar-item w3-button w3-padding"><i class="glyphicon glyphicon-user fa-fw"></i> Técnicos</a>
   	</sec:authorize>
   	<a href="#" class="w3-hover-theme w3-bar-item w3-button w3-padding"><i class="fa fa-file-text fa-fw"></i> Relatórios</a>	
  </div>
</nav>
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<style type="text/css"> 
a:link 
{text-decoration:none;} 
</style>

</sec:authorize>
