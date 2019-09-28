<%--
 * edit.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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
	
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
	
<security:authorize access="hasRole('USER')">

	<form:form action="announcement/user/edit.do" modelAttribute="announcement">
	
		<form:hidden path="id" />
		<form:hidden path="version" />	
		<form:hidden path="moment" />
		
		<acme:textbox code="announcement.title" path="title" />
		<acme:textarea code="announcement.description" path="description" />
		<acme:select code="announcement.rendezvous" path="rendezvouses" items="${rendezvouses}" itemLabel="name" id="rendezvouses" />

		<acme:submit code="announcement.save" name="save" />
		<acme:cancel code="announcement.cancel" url="announcement/user/list.do" />
		
		<jstl:if test="${announcement.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="announcement.delete" />"
				onclick="return confirm('<spring:message code="anouncement.delete.confirm" />')" />&nbsp;
		</jstl:if>
		<br />
	
	</form:form>
	
</security:authorize>
	