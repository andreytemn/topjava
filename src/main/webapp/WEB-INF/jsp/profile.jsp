<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h2>${userTo.name} <fmt:message key="app.profile"/></h2>

            <div class="view-box">
                <form:form modelAttribute="userTo" class="form-horizontal" method="post" action="profile"
                           charset="utf-8" accept-charset="UTF-8">

                    <fmt:message key="users.name" var="userName"/>
                    <topjava:inputField label='${userName}' name="name"/>

                    <fmt:message key="users.email" var="userEmail"/>
                    <topjava:inputField label='${userEmail}' name="email"/>

                    <fmt:message key="users.password" var="userPassword"/>
                    <topjava:inputField label='${userPassword}' name="password" inputType="password"/>

                    <fmt:message key="users.caloriesPerDay" var="caloriesPerDay"/>
                    <topjava:inputField label='${caloriesPerDay}' name="caloriesPerDay" inputType="number"/>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <button type="submit" class="btn btn-primary"><fmt:message key="common.update"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
