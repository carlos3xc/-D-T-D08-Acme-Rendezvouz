<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
 
	<display:table name="users" id="row" requestURI="register/listUser.do" pagesize="5">

	<spring:message code="user.name" var="name" />
	<display:column property="name" title="${name}" sortable="true" />

	<spring:message code="user.surname" var="surname" />
	<display:column property="surname" title="${surname}" sortable="true" />

	<spring:message code="explorer.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />

	<spring:message code="explorer.email" var="email" />
	<display:column property="email" title="${email}" sortable="true" />

	</display:table>
	
	