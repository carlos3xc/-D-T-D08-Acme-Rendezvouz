<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<display:table name="rendezvouses" id="row" requestURI="rendezvous/list.do" pagesize="5">
		<security:authorize access="hasRole('USER')">
			<display:column >
			<jstl:if test="${row.isFinal == false}">
				<a href="rendezvous/user/edit.do?rendezvousId=${row.id}">
					<spring:message	code="rendezvous.edit" />
				</a><br/>
			</jstl:if>
					<a href="rendezvous/user/cancel.do?rendezvousId=${row.id}" onclick="return confirm('<spring:message code="rendezvous.cancel.confirm" />')">
						<spring:message	code="rendezvous.cancel" />
					</a>
		</display:column>	
		</security:authorize>
	
	
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
		
		<spring:message code="rendezvous.announcements" var="announcementsHeader"/>
		<display:column title="${announcementsHeader}" >
			<a href="announcement/list.do?rendezvousId=${row.id }"><spring:message code="rendezvous.announcement.more"/></a>
		</display:column>
		
		
		<spring:message code="rendezvous.listAttendants" var="attendantsHeader"/>
		<display:column title="${attendantsHeader}">
			<jstl:forEach var="x" items="${row.listAttendants}">
					<a href="profile/info.do?actorId=${x.id }">${x.userAccount.username}</a>
			</jstl:forEach>
		</display:column>
		
		<spring:message code="rendezvous.creator" var="userHeader"/>
		<display:column title="${userHeader}">
			<a href="profile/info.do?actorId=${row.user.id }">${row.user.userAccount.username}</a>
		</display:column>
		

	</display:table>
		
	<security:authorize access="hasRole('USER')">	
		<a href="rendezvous/user/create.do"><spring:message code="rendezvous.create"/></a>
	</security:authorize>
	

