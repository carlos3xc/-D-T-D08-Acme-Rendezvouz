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

	<form:form action="comment/user/edit.do" modelAttribute="comment">
	
		<form:hidden path="id" />
		<form:hidden path="version" />	
		<form:hidden path="moment" />
		<form:hidden path="user" />
		<form:hidden path="replies" />
		
		<acme:textbox code="comment.picture" path="picture" />
		<acme:textarea code="comment.text" path="text" />

		<acme:submit code="comment.save" name="save" />
		<acme:cancel code="comment.cancel" url="rendezvous/display.do?rendezvousId=${rendezvous.id}" />
		
		<jstl:if test="${comment.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="comment" />"
				onclick="return confirm('<spring:message code="comment.delete.confirm" />')" />&nbsp;
		</jstl:if>
		<br />
	
	</form:form>
	
</security:authorize>
	