<%--
  Created by IntelliJ IDEA.
  User: AnhAnh
  Date: 12/18/2021
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <h1 style="text-align: center; color: maroon;">Product Line List</h1>
    <br><br>
    <div style="margin-left: 48px">
        <a class="btn btn-success" href="<%=request.getContextPath()%>/new">Add New ProductLine</a>
    </div>
    <br>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Productline</th>
            <th>TextDescription</th>
            <th>htmlDescription</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listProductLine}" var="p">
            <tr>
                <td>${p.productLine}</td>
                <td>${p.textDescription}</td>
                <td>${p.htmlDescription}</td>
                <td><a  class="btn btn-primary" href="edit?productLine=${p.productLine}">Edit</a></td>
                <td><a class="btn btn-danger" href="delete?productLine=${p.productLine}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<%--<script>--%>
<%--    function showMess(id) {--%>
<%--        var option = confirm("are you sure to delete?");--%>
<%--        if (option === true) {--%>
<%--            window.location.href = 'delete?productLine=' + id;--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
</html>
