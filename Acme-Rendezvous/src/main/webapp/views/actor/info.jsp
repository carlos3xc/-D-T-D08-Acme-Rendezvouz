<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
		
	<b><spring:message code="actor.name" /></b>: <jstl:out value="${actor.name}"></jstl:out><br/>
	<b><spring:message code="actor.surname" /></b>: <jstl:out value="${actor.surname}"></jstl:out><br/>
	<b><spring:message code="actor.email" /></b>: <jstl:out value="${actor.email}"></jstl:out><br/>
	<b><spring:message code="actor.phone" /></b>: <jstl:out value="${actor.phoneNumber}"></jstl:out><br/>
	<b><spring:message code="actor.address" /></b>: <jstl:out value="${actor.postalAddress}"></jstl:out><br/>	
	
	<spring:url var="url" value="profile/edit.do">
		<spring:param name="actorId" value="${actor.id}"/>
	</spring:url>
	<button type="button" onclick="javascript: window.location.replace('${url}')"><spring:message code="actor.edit"/></button>

	<security:authorize access="hasRole('USER')">
	<h3>	<spring:message code="actor.rendezvouses" /></h3><br/>		
		<display:table name="rendezvouses" id="row" requestURI="actor/list.do" pagesize="5">
			<spring:message code="rendezvous.name" var="nameHeader"/>
			<display:column property="name" title="${nameHeader}" sortable="false" />
		
			<spring:message code="rendezvous.description" var="descriptionHeader"/>
			<display:column property="description" title="${descriptionHeader}" sortable="false" />
		
			<spring:message code="rendezvous.moment" var="momentHeader" />
			<spring:message code="rendezvous.moment.format" var="formatMoment"/>
			<display:column property="moment" title="${momentHeader }"  sortable="true"  format="{0,date,${formatMoment} }" />

			<spring:message code="rendezvous.gpsCoordinates" var="gpsCoordinateHeader"/>
			<display:column title="${gpsCoordinateHeader}" >
				${row.location.latitude} ${row.location.longitude}
			</display:column>
		
			<spring:message code="rendezvous.listAttendants" var="attendantsHeader"/>
			<display:column title="${attendantsHeader}">
			<jstl:forEach var="x" items="${row.listAttendants}">
					<a href="profile/info.do?actorId=${x.key }">${x.value}</a>
			</jstl:forEach>
			</display:column>
		</display:table>	
	</security:authorize>
	
