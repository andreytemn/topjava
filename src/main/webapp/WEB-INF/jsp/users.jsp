<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/dataTables.bootstrap.min.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="users.title"/></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" onclick="add('<fmt:message key="users.add"/>')"><fmt:message key="users.add"/></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="users.name"/></th>
                        <th><fmt:message key="users.email"/></th>
                        <th><fmt:message key="users.roles"/></th>
                        <th><fmt:message key="users.active"/></th>
                        <th><fmt:message key="users.registered"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${users}" var="user">
                        <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
                        <tr>
                            <td><c:out value="${user.name}"/></td>
                            <td><a href="mailto:${user.email}">${user.email}</a></td>
                            <td>${user.roles}</td>
                            <td>
                                <input type="checkbox"
                                       <c:if test="${user.enabled}">checked</c:if> onclick="enable($(this), ${user.id})"/>
                            </td>
                            <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                            <td><a class="btn btn-xs btn-primary"  onclick="updateRow(${user.id})"><fmt:message key="common.update"/></a></td>
                            <td><a class="btn btn-xs btn-danger" onclick="deleteRow(${user.id})"><fmt:message key="common.delete"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><fmt:message key="users.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<fmt:message key="users.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><fmt:message key="users.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<fmt:message key="users.email"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><fmt:message key="users.password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="<fmt:message key="users.password"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()"><fmt:message key="common.save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var edit_title ='<fmt:message key="users.edit"/>';
</script>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = 'ajax/admin/users/';
    var datatableApi;

    function updateTable() {
        $.get(ajaxUrl, updateTableByData);
    }

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "<fmt:message key="common.update"/>",
                    "orderable": false
                },
                {
                    "defaultContent": "<fmt:message key="common.delete"/>",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        });
        makeEditable();
    });
</script>
</html>