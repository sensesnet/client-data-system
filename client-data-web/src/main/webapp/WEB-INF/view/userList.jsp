<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Simple test system</title>
</head>

<style type="text/css">

    body {
        text-align: center;
        vertical-align: center;
        font-size: 13px;
        line-height: 1.47059;
        font-weight: 400;
        letter-spacing: -0.022em;
        font-family: "SF Pro Text", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
        background-color: white;
        color: #333333;
        font-style: normal;
        top: 50%;
        left: 50%;
    }

    h1 {
        text-align: center;
        vertical-align: center;
        color: #333333;
        display: block;
        width: 100%;
        margin-left: auto;
        margin-right: auto;
        font-size: 44px;
        line-height: 1.09091;
        font-weight: 600;
        letter-spacing: -.002em;
        font-family: "SF Pro Display", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
    }

    h3 {
        text-align: center;
        vertical-align: center;
        font-size: 28px;
        letter-spacing: -0.022em;
        font-family: "SF Pro Display", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
        background-color: white;
        color: #333333;
        font-style: normal;
        margin: 0px 0px 7px;
    }

    a {
        z-index: 4;
        font-size: 16px;
        line-height: 3;
        text-decoration: none;
        text-decoration-line: none;
        text-decoration-style: initial;
        text-decoration-color: initial;
        color: -webkit-link;
        cursor: pointer;
        color: #0070c9;
        display: inline-block;
        text-decoration: inherit;
        text-decoration-line: inherit;
        margin-left: 30px;
        white-space: nowrap;
    }

    .tableLink {
        z-index: 4;
        font-size: 13px;
        line-height: 3;
        text-decoration: none;
        text-decoration-style: initial;
        text-decoration-color: initial;
        text-align: left;
        color: -webkit-link;
        cursor: pointer;
        color: #0070c9;
        display: inline-block;
        text-decoration: inherit;
        text-decoration-line: inherit;
        white-space: nowrap;
    }

    hr {
        border: 0;
        height: 0;
        border-bottom: 1px solid #dedede;
        clear: both;
    }
</style>

<body>
<h1>Online Testing Platform</h1>
<div>
    <div class="links">
        <a href="showFormForAdd"><span class="headLink">Add new User</span></a>
        <a href="/home"><span class="headLink">Admin main page</span></a>
        <a href="/logout"><span class="headLink">Sign Out</span></a>
    </div>
</div>
<c:if test="${not empty errorMessage}">
    <c:out value="${errorMessage}"></c:out>
</c:if>
<c:if test="${not empty message}">
    <c:out value="${message}"></c:out>
</c:if>
<form action="Controller">
    <center>
        <h2>List of User</h2>
        <table border="0" width="30%" cellpadding="5">
            <tr>
                <th>first_name</th>
                <th>last_name</th>
                <th>email</th>
                <th>address</th>
                <th>birthday_date</th>
                <th>phone_number</th>
                <th>role</th>
                <th>action</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.getUserName()}</td>
                    <td>${user.getUserSurname()}</td>
                    <td>${user.getUserLogin()}</td>
                    <td>${user.getUserAddress()}</td>
                    <td>${user.getUserBirthday()}</td>
                    <td>${user.getUserPhone()}</td>
                    <td>${user.getUserRole()}</td>
                    <td>
                        <!-- construct an "update" link with customer id -->
                        <c:url var="edit" value="/user/edit">
                            <c:param name="userId" value="${user.getUserId()}" />
                        </c:url>

                        <!-- construct an "delete" link with customer id -->
                        <c:url var="delete" value="/user/delete">
                            <c:param name="userId" value="${user.getUserId()}" />
                        </c:url>

                        <a href="${edit}"><span class="tableLink">Edit</span></a>
                        <a href="${delete}"/><span class="tableLink">Remove</span></a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:url var="first" value="/user/list"/>
        <a href="${first}">First</a>

        <c:url var="next" value="/user/list/${nextPage}"/>
        <a href="${next}">Next</a>

        <hr align="center" size="1px" width="500px">
        <p>Online test system</p>
    </center>
</form>
</body>
</html>
