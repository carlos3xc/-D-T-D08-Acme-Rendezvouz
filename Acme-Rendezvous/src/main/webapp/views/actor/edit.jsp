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
	<form:hidden path="isSuspicious"/>
	<form:hidden path="isBanned"/>
	<form:hidden path="userAccount"/>
	<form:hidden path="socialIdentities"/>	
	<form:hidden path="folders" />
	
	<jstl:if test="${authority == 'EXPLORER'}">
		<form:hidden path="finder"/>
		<form:hidden path="contacts"/>
		<form:hidden path="applications"/>
		<form:hidden path="stories"/>
		<form:hidden path="isEnrol"/>
	</jstl:if>
	<jstl:if test="${authority == 'MANAGER'}">
		<form:hidden path="notes"/>
		<form:hidden path="trips"/>
		<form:hidden path="survivalClasses"/>
	</jstl:if>
	<jstl:if test="${authority == 'RANGER'}">
		<form:hidden path="curricula"/>
		<form:hidden path="trips"/>
	</jstl:if>
			

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
		
	<form:label path="phone">
		<spring:message code="actor.phone" />:
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
		
	<form:label path="address">
		<spring:message code="actor.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	<jstl:if test="${authority == 'EXPLORER'}">
		<input type="submit" name="saveExplorer" value="<spring:message code="actor.save" />" />&nbsp; 
	</jstl:if>
	<jstl:if test="${authority == 'MANAGER'}">
		<input type="submit" name="saveManager" value="<spring:message code="actor.save" />" />&nbsp; 
	</jstl:if>
	<jstl:if test="${authority == 'ADMINISTRATOR'}">
		<input type="submit" name="saveAdministrator" value="<spring:message code="actor.save" />" />&nbsp; 
	</jstl:if>
	<jstl:if test="${authority == 'RANGER'}">
		<input type="submit" name="saveRanger" value="<spring:message code="actor.save" />" />&nbsp; 
	</jstl:if>
		<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: window.location.replace('profile/info.do')" />
	<br />

</form:form>