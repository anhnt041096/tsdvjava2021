<%--
  Created by IntelliJ IDEA.
  User: AnhAnh
  Date: 12/20/2021
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Payments Manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<h1 style="text-align: center;">
    <a style="color: maroon;" href="<%=request.getContextPath()%>/list"> Payments Manager </a>
</h1>
    <br><br>
    <div style="margin-left: 48px">
        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add new payment </a>
    </div>
    <br>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Customer number</th>
                <th>Check number</th>
                <th>Payment date</th>
                <th>Amount</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${listP}" var="o">
            <tr>
                <td>${o.customerNumber}</td>
                <td>${o.checkNumber}</td>
                <td>${o.paymentDate}</td>
                <td>${o.amount}</td>
                <td>
                    <a href="edit?customerNumber=${o.customerNumber}&checkNumber=${o.checkNumber}"
                       class="btn btn-btn btn-primary">Edit</a>
                    <a href="delete?customerNumber=${o.customerNumber}&checkNumber=${o.checkNumber}"
                       class="btn btn-btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
