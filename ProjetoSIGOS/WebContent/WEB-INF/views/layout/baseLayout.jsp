<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/resources/img/spring.ico" var="icon"></spring:url>

<!-- JS -->

<spring:url value="/resources/js/jquery.min.js" var="jqueryJS"></spring:url>
<spring:url value="/resources/js/consulta_cep.js" var="consulta_cepJS"></spring:url>
<spring:url value="/resources/js/maskMoney.js" var="maskMoneyJS"></spring:url>
<spring:url value="/resources/js/consulta_equip_cliente.js" var="consultaEquipClienteJS"></spring:url>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJS"></spring:url>
<spring:url value="/resources/js/pagination_table.js" var="paginationJS"></spring:url>
<spring:url value="/resources/js/operacoes_os.js" var="operacoes_osJS"></spring:url>
<spring:url value="/resources/js/operacoes_orcamento.js" var="operacoes_orcamentoJS"></spring:url>
<spring:url value="https://code.jquery.com/ui/1.12.1/jquery-ui.js" var="jquery_uiJS"></spring:url>

<!-- CSS -->

<spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrapCSS"></spring:url>
<spring:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" var="fontawesomeCSS"></spring:url>
<spring:url value="/resources/css/w3css.css" var="w3CSS"></spring:url>
<spring:url value="https://fonts.googleapis.com/css?family=Helvetica" var="fontesCSS"></spring:url>
<spring:url value="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" var="jqueryUICSS"></spring:url>
<spring:url value="/resources/css/w3-theme-blue-grey.css" var="w3ThemeBlueGreyCSS"></spring:url>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>

<script type="text/javascript"  charset="utf-8" src="${jqueryJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${bootstrapJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${consulta_cepJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${paginationJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${jquery_uiJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${operacoes_osJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${operacoes_orcamentoJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${maskMoneyJS}"></script>
<script type="text/javascript"  charset="utf-8" src="${consultaEquipClienteJS}"></script>

<link href="${bootstrapCSS}" rel="stylesheet" />
<link href="${fontawesomeCSS}" rel="stylesheet" />
<link href="${w3CSS}" rel="stylesheet" />
<link href="${fontesCSS}" rel="stylesheet" />
<link href="${jqueryUICSS}" rel="stylesheet" />
<link href="${w3ThemeBlueGreyCSS}" rel="stylesheet" />

<link rel="shortcut icon" href="${icon}" />
</head>
<body  class="w3-light-grey">
	<style>
		html,body,h1,h2,h3,h4,h5 {font-family: "Helvetica", sans-serif}
	</style>
		
	<div>
		<tiles:insertAttribute name="header" ignore="false"></tiles:insertAttribute>
	</div>

	<div >
		<tiles:insertAttribute name="menu" ignore="false"></tiles:insertAttribute>
	</div>

	<div <sec:authorize access="isAuthenticated()"> class="w3-main" style="margin-left:240px;margin-top:47px;"</sec:authorize>>	
		<tiles:insertAttribute name="body" ignore="false"></tiles:insertAttribute>
	</div>

	<div>
		<tiles:insertAttribute name="footer" ignore="false"></tiles:insertAttribute>
	</div>
	
	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
    		if (mySidebar.style.display === 'block') {
       			mySidebar.style.display = 'none';
       	 		overlayBg.style.display = "none";
    		} else {
        		mySidebar.style.display = 'block';
        		overlayBg.style.display = "block";
    		}
		}
		// Close the sidebar with the close button
		function w3_close() {
   	 		mySidebar.style.display = "none";
    		overlayBg.style.display = "none";
		}
	</script>
</body>
</html>