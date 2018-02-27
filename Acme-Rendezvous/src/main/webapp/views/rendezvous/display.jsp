<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
		
	<b><spring:message code="rendezvous.name" /></b>: <jstl:out value="${rendezvous.name}"></jstl:out><br/>
	<b><spring:message code="rendezvous.description" /></b>: <jstl:out value="${rendezvous.description}"></jstl:out><br/>
	<b><spring:message code="rendezvous.picture" /></b>: <jstl:out value="${rendezvous.picture}"></jstl:out><br/>
	<b><spring:message code="rendezvous.gpsCoordinates" /></b>: <jstl:out value="${rendezvous.gpsCoordinates.latitude}"/> - <jstl:out value="${rendezvous.gpsCoordinates.longitude}"/><br/>
	<b><spring:message code="rendezvous.isAdultContent" /></b>: <jstl:out value="${rendezvous.isAdultContent}"></jstl:out><br/>
	<b><spring:message code="rendezvous.flag" /></b>: <jstl:out value="${rendezvous.flag}"></jstl:out><br/>
	<b><spring:message code="rendezvous.creator" /></b>: <jstl:out value="${rendezvous.user.name}"></jstl:out><br/>

	<b><spring:message code="rendezvous.listAttendants" /></b>:
		<jstl:forEach var="x" items="${rendezvous.listAttendants}">
					<a href="profile/info.do?actorId=${x.id}">${x.userAccount.username}</a>, 
			</jstl:forEach><br/>
	
	
<h3>	<spring:message code="rendezvous.linkedRendezvous" /></h3>
	
	<!--  Lista de citas similares a la actual -->	
		<display:table name="rendezvous.linkedRendezvous" id="row" requestURI="rendezvous/display.do" pagesize="5">
			<spring:message code="rendezvous.name" var="nameHeader"/>
			<display:column property="name" title="${nameHeader}" sortable="false" />
		
			<spring:message code="rendezvous.description" var="descriptionHeader"/>
			<display:column property="description" title="${descriptionHeader}" sortable="false" />
		
			<spring:message code="rendezvous.moment" var="momentHeader" />
			<spring:message code="rendezvous.moment.format" var="formatMoment"/>
			<display:column property="moment" title="${momentHeader }"  sortable="true"  format="{0,date,${formatMoment} }" />

			<spring:message code="rendezvous.gpsCoordinates" var="gpsCoordinateHeader"/>
			<display:column title="${gpsCoordinateHeader}" >
				${row.gpsCoordinates.latitude} ${row.gpsCoordinates.longitude}
			</display:column>
		
			<spring:message code="rendezvous.listAttendants" var="attendantsHeader"/>
			<display:column title="${attendantsHeader}">
			<jstl:forEach var="x" items="${row.listAttendants}">
					<a href="profile/info.do?actorId=${x.id}">${x.userAccount.username}</a>
			</jstl:forEach>
			</display:column>
		</display:table>	
		
		<!-- Lista de anuncios de la cita -->
		
		<h3>	<spring:message code="rendezvous.announcements" /></h3>

	<display:table name="rendezvous.announcements" id="row" requestURI="rendezvous/display.do" pagesize="5">
	
		<spring:message code="announcement.title" var="titleHeader"/>
		<display:column property="title" title="${titleHeader}" sortable="false" />
		
		<spring:message code="announcement.description" var="descriptionHeader"/>
		<display:column property="description" title="${descriptionHeader}" sortable="false" />
		
		<spring:message code="rendezvous.moment" var="momentHeader" />
		<spring:message code="rendezvous.moment.format" var="formatMoment"/>
		<display:column property="moment" title="${momentHeader }"  sortable="true"  format="{0,date,${formatMoment} }" />

	</display:table>
	
	<h3>	<spring:message code="rendezvous.comments" /></h3>
	
	
	<display:table name="rendezvous.comments" id="row" requestURI="rendezvous/display.do" pagesize="5">
	
		<display:column>
		<security:authorize access="hasRole('ADMIN')">
				<a href="rendezvous/administrator/delete.do?rendezvousId=${rendezvous.id}">
					<spring:message	code="rendezvous.delete" />
				</a><br/>
			</security:authorize>
		</display:column>
		<display:column>
			<b><jstl:out value="${row.user.userAccount.username}"/></b> - <jstl:out value="${row.moment}"/><br/>
	
			<jstl:out value="${row.text}"/><br/>
			
			<spring:message code="rendezvous.picture"/>: 
			<jstl:out value="${row.picture}"/><br/><br/>
			
		</display:column>
	</display:table>
	<jstl:if test="${comment == 'OK' }">
		<a href="comment/user/create.do"><spring:message code="rendezvous.comment.create"/></a>
	</jstl:if>
		
	
