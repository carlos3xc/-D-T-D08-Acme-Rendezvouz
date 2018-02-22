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

	<form:label path="name">
		<spring:message code="rendezvous.name"/>:
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="rendezvous.name" />
	<br/>
	
	<form:label path="description">
		<spring:message code="rendezvous.description"/>:
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="rendezvous.description" />
	<br/>
	
	<spring:message code="rendezvous.moment.format" var="momentFormat"/>
	<form:label path="moment">
		<spring:message code="rendezvous.moment" />:
	</form:label>
	<form:input path="moment" placeholder="${momentFormat}"/>
	<form:errors cssClass="error" path="moment" />
	<br />
	
	<form:label path="picture">
	<spring:message code="rendezvous.picture"/>:
	</form:label>
	<form:textarea path="picture"/>
	<form:errors cssClass="error" path="rendezvous.picture" />
	<br/>
	
	<form:label path="gpsCoordinates">
	<spring:message code="rendezvous.gpsCoordinates"/>:
	</form:label> <br/>
	
	<form:label path="gpsCoordinates.latitude">
	<spring:message code="rendezvous.gpsCoordinates.latitude"/>:
	</form:label>	
	<form:input path="gpsCoordinates.latitude"/>
	<form:errors cssClass="error" path="rendezvous.gpsCoordinates.latitude" />
	<br/>
	<form:label path="gpsCoordinates.longitude">
	<spring:message code="rendezvous.gpsCoordinates.longitude"/>:
	</form:label>	
	<form:input path="gpsCoordinates.longitude"/>
	<form:errors cssClass="error" path="rendezvous.gpsCoordinates.longitude" />
	<br/>
	
	<form:label path="finalMode">
	<spring:message code="rendezvous.finalMode"/>:
	</form:label>	
	<form:checkbox path="finalMode" />
	<form:errors cssClass="error" path="rendezvous.finalMode" />
	<br/>
	
	<form:label path="isAdultContent">
	<spring:message code="rendezvous.isAdultContent"/>:
	</form:label>	
	<form:checkbox path="isAdultContent" />
	<form:errors cssClass="error" path="rendezvous.isAdultContent" />
	<br/>
	
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
	