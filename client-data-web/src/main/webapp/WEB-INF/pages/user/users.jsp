<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table table-hover">
    <thead>
    <tr>
        <th>id</th>
        <th>user name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.userName}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:url var="first" value="/user/list"/>
<a href="${first}" class="btn btn-primary" role="button">First</a>

<c:url var="next" value="/user/list/${nextPage}"/>
<a href="${next}" class="btn btn-primary" role="button">Next</a>