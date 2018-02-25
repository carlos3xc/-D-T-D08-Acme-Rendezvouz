<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Rendezvous Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="rendezvous/administrator/list.do"><spring:message code="master.page.user.list.rendezvous" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">			
			<li><a class="fNiv"><spring:message code="master.page.rendezvous" /></a>	
			<ul>
					<li class="arrow"></li>
					<li><a href="rendezvous/list.do"><spring:message code="master.page.user.list.rendezvous" /></a></li>
					<li><a href="rendezvous/user/create.do"><spring:message code="master.page.user.create.rendezvous" /></a></li>					
			</ul>
			<li><a class="fNiv"><spring:message code="master.page.list.announcement" /></a>	
				<ul>
					<li class="arrow"></li>
					<li><a href="announcement/list.do"><spring:message code="master.page.user.list.announcement" /></a>
					<li><a href="announcement/user/create.do"><spring:message code="master.page.user.create.announcement" /></a>
						
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="register/createUser.do"><spring:message code="master.page.register" /></a>
			<li><a class="fNiv" href="rendezvous/list.do"><spring:message code="master.page.list.rendezvous" /></a>
			<li><a class="fNiv" href="announcement/list.do"><spring:message code="master.page.list.announcement" /></a>
		</security:authorize>
		
		<security:authorize access="permitAll">
						<li><a class="fNiv" href="user/list.do"><spring:message code="master.page.list.user" /></a>
			
		</security:authorize>
		
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/info.do"><spring:message code="master.page.profile" /></a></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

