<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">

                    <c:url var="index" value="/index"/>
                    <li><a href="${index}"><spring:message code="home"/></a></li>

                    <c:url var="users" value="/user/list"/>
                    <li><a href="${users}"><spring:message code="users"/></a></li>

                    <c:url var="registration" value="/user/add"/>
                    <li><a href="${registration}"><spring:message code="registration"/></a></li>

                    <c:url var="logout" value="/user/logout"/>
                    <li><a href="${logout}"><spring:message code="logout"/></a></li>

                    <c:url var="tasks" value="/task/list"/>
                    <li><a href="${tasks}"><spring:message code="tasks"/></a></li>

                    <c:url var="party" value="/party/list"/>
                    <li><a href="${party}"><spring:message code="party"/></a></li>

                    <spring:message code="locale.change" var="changeLocale" htmlEscape="true"/>
                    <c:url var="locale" value="/index?locale=${changeLocale}"/>
                    <li><a href="${locale}"><spring:message code="locale.name"/></a></li>

                    <sec:authorize access="hasRole('PRIVATE')">
                        <li>
                            <p class="navbar-text"><spring:message code="hello"/> <sec:authentication property="principal.username"/></p>
                        </li>
                    </sec:authorize>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</header>