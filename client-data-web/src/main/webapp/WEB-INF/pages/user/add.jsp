<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="saveUser" value="/user/add"/>
<s:form action="${saveUser}" modelAttribute="user" method="post" class="form-horizontal" role="form">
    <fieldset>
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">user name:</label>
            <div class="col-sm-10">
                <s:errors path="userName" cssStyle="color: red"/>
                <s:input id="userName" type="text" path="userName" class="form-control"/><br/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">password :</label>
            <div class="col-sm-10">
                <s:input id="password" type="password" maxlength="255" path="password" class="form-control"/><br/></div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Sing up</button>
            </div>
        </div>
    </fieldset>
</s:form>