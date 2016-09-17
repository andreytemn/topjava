<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 14.09.16
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meal List</title>
</head>
<body>
<h1>Meal list</h1>
<table border="1px solid black">
    <c:forEach var="meal" items="${requestScope.list}">
        <%String trClass = "color:green;";%>
        <c:if test="${meal.isExceed()}">
            <%trClass = "color:red;";%>
        </c:if>
        <tr style="<%=trClass%>">
            <td>${meal.getDateTime()}</td><td>${meal.getDescription()}</td><td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>
<h2><a href="index.html">Home</a></h2>
</body>
</html>
