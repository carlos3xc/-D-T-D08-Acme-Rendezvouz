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
	<form:form action="announcement/user/edit.do" modelAttribute="announcement">
	
	<form:hidden path="id" />
	<form:hidden path="version" />	
	<form:hidden path="moment" />

	<form:label path="title">
		<spring:message code="announcement.title"/>:
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="announcement.title" />
	<br/>
	
	<form:label path="description">
		<spring:message code="announcement.description"/>:
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="announcement.description" />
	<br/>
	
	<form:select path="rendezvouses">
		<form:option label="----" value="0"/>
		<form:options items="${rendezvouses}" itemlabel="name" itemValue="id"/>
	</form:select>
	
	<input type="submit" name="save"
		value="<spring:message code="announcement.save" />" />&nbsp; 
	<jstl:if test="${announcement.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="announcement.delete" />"
			onclick="return confirm('<spring:message code="anouncement.delete.confirm" />')" />&nbsp;
	</jstl:if>

		<input type="button" name="cancel"
		value="<spring:message code="announcement.cancel" />"
		onclick="javascript: window.location.replace('announcement/user/list.do')" />
	<br />
	
	</form:form>
	
	</security:authorize>
	