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

<form:form action="profile/edit.do" modelAttribute="actor">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="userAccount"/>

	<form:label path="name">
		<spring:message code="actor.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />	

	<form:label path="surname">
		<spring:message code="actor.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="actor.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />	
		
	<form:label path="phoneNumber">
		<spring:message code="actor.phone" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />
		
	<form:label path="postalAddress">
		<spring:message code="actor.address" />:
	</form:label>
	<form:input path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	
	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="saveAdmin" value="<spring:message code="actor.save" />" />&nbsp; 
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
		<input type="submit" name="saveUser" value="<spring:message code="actor.save" />" />&nbsp; 
	</security:authorize>
	
	<input type="button" name="cancel" value="<spring:message code="actor.cancel" />"
		onclick="javascript: window.location.replace('profile/info.do')" />
	<br />

</form:form>