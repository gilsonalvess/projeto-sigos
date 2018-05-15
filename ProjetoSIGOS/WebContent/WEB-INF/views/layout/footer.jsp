<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">

<footer class="w3-container w3-padding-16 w3-light-grey">
	<p class=text-center>© 2017 - Gilson Alves | gilsonalves.toc86@gmail.com</p>
</footer>
</sec:authorize>