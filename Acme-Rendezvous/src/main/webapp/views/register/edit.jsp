<%--
 * edit.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="register/edit.do" modelAttribute="user">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="userAccount"/>
	
	<acme:textbox code="register.name" path="name"/>
	<acme:textbox code="register.surname" path="surname"/>
	<acme:textbox code="register.email" path="email"/>
	<acme:textbox code="register.phone" path="phoneNumber"/>
	<acme:textbox code="register.address" path="postalAddress"/>
	<acme:textbox code="register.username" path="userAccount.username"/>
	<acme:textbox code="register.password" path="userAccount.password"/>

	<input type="submit" name="saveUser" value="<spring:message code="register.save" />"/>
		
	<input type="button" name="cancel"
		value="<spring:message code="register.cancel" />"
		onclick="javascript: window.location.replace('')" />
	<br />

</form:form>
