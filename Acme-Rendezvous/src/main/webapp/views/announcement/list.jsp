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
<!-- announcements es una variable donde están los anuncios asociados a su rendezvous -->
	<display:table name="announcements" id="row" requestURI="announcement/user/list.do" pagesize="5">
			
			<display:column >
					<a href="announcement/user/delete.do?announcementId=${row.id}" onclick="return confirm('<spring:message code="announcement.delete.confirm" />')">
						<spring:message	code="announcement.delete" />
					</a>
		</display:column>
	
	
		<spring:message code="announcement.title" var="titleHeader"/>
		<display:column property="title" title="${titleHeader}" sortable="false" />
		
		<spring:message code="announcement.description" var="descriptionHeader"/>
		<display:column property="description" title="${descriptionHeader}" sortable="false" />
		
		<spring:message code="announcement.moment" var="momentHeader" />
		<spring:message code="announcement.moment.format" var="formatMoment"/>
		<display:column property="moment" title="${momentHeader }"  sortable="true"  format="{0,date,${formatMoment} }" />

	</display:table>
		
	<a href="announcement/user/create.do"><spring:message code="announcement.create"/></a>
	</security:authorize>
	
	<security:authorize access="isAnonymous()">

	<display:table name="announcements" id="row" requestURI="announcement/list.do" pagesize="5">
	
		<spring:message code="announcement.title" var="titleHeader"/>
		<display:column property="title" title="${titleHeader}" sortable="false" />
		
		<spring:message code="announcement.description" var="descriptionHeader"/>
		<display:column property="description" title="${descriptionHeader}" sortable="false" />
		
		<spring:message code="announcement.moment" var="momentHeader" />
		<spring:message code="announcement.moment.format" var="formatMoment"/>
		<display:column property="moment" title="${momentHeader }"  sortable="true"  format="{0,date,${formatMoment} }" />

	</display:table>
		
	</security:authorize>
	

