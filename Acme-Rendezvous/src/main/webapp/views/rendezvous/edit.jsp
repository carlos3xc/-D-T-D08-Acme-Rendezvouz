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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
	<security:authorize access="hasRole('USER')">
	<form:form action="rendezvous/user/edit.do" modelAttribute="rendezvous">
	
	<form:hidden path="id" />
	<form:hidden path="version" />	
	<form:hidden path="user" />
	<form:hidden path="listAttendants"/>
	<form:hidden path="flag"/>
	<form:hidden path="comments"/>
	<form:hidden path="flag"/>
	<form:hidden path="questions"/>
	<form:hidden path="announcements"/>
	
	<acme:textbox code="rendezvous.name" path="name"/>
	<acme:textbox code="rendezvous.description" path="description"/>
	<acme:textbox code="rendezvous.moment" path="moment"/>
	<!--  
	<spring:message code="rendezvous.moment.format" var="momentFormat"/>
	<form:label path="moment">
		<spring:message code="rendezvous.moment" />:
	</form:label>
	<form:input path="moment" placeholder="${momentFormat}"/>
	<form:errors cssClass="error" path="moment" />
	<br /> -->
	
	<acme:textbox code="rendezvous.picture" path="picture"/>
	
	<form:label path="gpsCoordinates">
	<spring:message code="rendezvous.gpsCoordinates"/>:
	</form:label> <br/>
	
	<acme:textbox code="rendezvous.gpsCoordinates.latitude" path="gpsCoordinates.latitude"/>
	<acme:textbox code="rendezvous.gpsCoordinates.longitude" path="gpsCoordinates.longitude"/>
	
	<acme:checkbox code="rendezvous.finalMode" path="finalMode"/>
	<acme:checkbox code="rendezvous.isAdultContent" path="isAdultContent"/>
	
	<input type="submit" name="save"
		value="<spring:message code="rendezvous.save" />" />&nbsp; 
	<jstl:if test="${rendezvous.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="rendezvous.delete" />"
			onclick="return confirm('<spring:message code="rendezvous.delete.confirm" />')" />&nbsp;
	</jstl:if>

		<input type="button" name="cancel"
		value="<spring:message code="rendezvous.cancel" />"
		onclick="javascript: window.location.replace('rendezvous/user/list.do')" />
	<br />
	
	</form:form>
	
	</security:authorize>
	