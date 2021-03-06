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

    input[type=password], input[type=submit], input[type=text] {

        font-size: 17px;
        line-height: 1.29412;
        font-weight: 400;
        letter-spacing: -.021em;
        font-family: SF Pro Text, SF Pro Icons, Helvetica Neue, Helvetica, Arial, sans-serif;
        display: inline-block;
        box-sizing: border-box;
        vertical-align: top;
        width: 100%;
        height: 34px;
        margin-bottom: 14px;
        padding-left: 15px;
        padding-right: 15px;
        color: #333;
        text-align: left;
        border: 1px solid #d6d6d6;
        border-radius: 4px;
        background: #fff;
        background-clip: padding-box;
    }

    input[type=submit] {
        background-color: rgb(240, 240, 240);
        text-align: center;
        vertical-align: center;
    }

    input[type=reset] {
        font-size: 17px;
        font-weight: 400;
        font-family: SF Pro Text, SF Pro Icons, Helvetica Neue, Helvetica, Arial;
        width: 100%;
        height: 34px;
        margin-bottom: 14px;
        padding-left: 15px;
        padding-right: 15px;
        color: #333;
        background: #fff;
        text-align: right;
        vertical-align: center;
        border: 0;
        outline: none;
    }

    hr {
        border: 0;
        height: 0;
        border-bottom: 1px solid #dedede;
        clear: both;
    }
</style>
<body>
<h3>Online Testing Platform</h3>
<div>
    <div class="links">
        <a href="list"><span class="headLink">List of User</span></a>
        <a href=home"><span class="headLink">Admin main page</span></a>
        <a href="logout"><span class="headLink">Sign Out</span></a>
    </div>
</div>
<s:form action="edit" modelAttribute="user" role="form" method="POST">
    <center>
        <h2>User Details</h2>
        <table border="0" width="30%" cellpadding="5">
            <tr>
                <td>
                    <span>User Id (read-only)</span>
                    <s:input id="userId"
                             type="text"
                             path="userId"
                             readonly="readonly"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <span>Name</span>
                    <s:input id="userName"
                             type="text"
                             path="userName"
                             pattern="^[a-zA-Z]+$"
                             title="Please, set your first name. Use only later."
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <span>Surname</span>
                    <s:input id="userSurname"
                             type="text"
                             path="userSurname"
                             pattern="^[a-zA-Z]+$"
                             title="Please, set your second name. Use only later."
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <span>Login (read-only)</span>
                    <s:input id="userLogin"
                             type="text"
                             path="userLogin"
                             readonly="readonly"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <span>Address</span>
                    <s:input id="userAddress"
                             type="text"
                             path="userAddress"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <span>Birthday</span>
                    <s:input id="userBirthday"
                             type="text"
                             path="userBirthday"
                             pattern="^\d{4}-\d{2}-\d{2}$"
                             title="'Please, use format like 'yyyy-mm-dd'"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <span>Phone</span>
                    <s:input id="userPhone"
                             type="text"
                             path="userPhone"
                             pattern="(8 0(25|29|33|34) ([0-9]{3}( [0-9]{2}){2}))"
                             title="like, '8 0xx xxx xx xx'"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <span>Role</span>
                    <s:input id="userRole"
                             type="text"
                             path="userRole"
                    />
                        <%--                             pattern="^(ADMIN|USER)"--%>
                        <%--                             title="'ADMIN' or 'USER'"--%>
                </td>
            </tr>

            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
        <hr align="center" size="1px" width="500px">
        <p>Online test system</p>
    </center>
</s:form>
</body>
</html>
